package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.event.CartAllCheckedEvent
import com.yizhipin.base.event.UpdateTotalPriceEvent
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.YuanFenConverter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.CartGoods
import com.yizhipin.goods.injection.component.DaggerCartComponent
import com.yizhipin.goods.injection.module.CartModule
import com.yizhipin.goods.presenter.CartPresenter
import com.yizhipin.goods.presenter.view.CartView
import com.yizhipin.goods.ui.adapter.CartGoodsAdapter
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class CartFragment : BaseMvpFragment<CartPresenter>(), CartView {

    private lateinit var mCartGoodsAdapter: CartGoodsAdapter

    private var mTotalPrice: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_cart, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(activity!!)
        mCartGoodsAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mCartGoodsAdapter

        mAllCheckedCb.onClick {
            for (item in mCartGoodsAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mCartGoodsAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mHeaderBar.getRightTv().onClick {
            refreshEditStatus()
        }

        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mCartGoodsAdapter.dataList
                    .filter { it.isSelected }
                    .mapTo(cartIdList) { it.id }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mBasePresenter.deleteCartList(cartIdList)
            }
        }

        mSettleAccountsBtn.onClick {
            val cartIdList: MutableList<CartGoods> = arrayListOf()
            mCartGoodsAdapter.dataList
                    .filter { it.isSelected }
                    .mapTo(cartIdList) { it }
            if (cartIdList.size == 0) {
                toast("请选择需要提交的数据")
            } else {
                mBasePresenter.submitCart(cartIdList, mTotalPrice)
            }
        }
    }

    private fun refreshEditStatus() {
        val isEfitStatus = getString(R.string.edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEfitStatus.not())
        mSettleAccountsBtn.setVisible(isEfitStatus.not())
        mDeleteBtn.setVisible(isEfitStatus)
        mHeaderBar.getRightTv().text = if (isEfitStatus) getString(R.string.complete) else getString(R.string.edit)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(mActivityComponent).cartModule(CartModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mBasePresenter.getCartList()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAllCheckedCb.isChecked = false
            mCartGoodsAdapter.setData(result)
            mHeaderBar.getRightTv().visibility = View.VISIBLE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightTv().visibility = View.GONE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        updateTotalPrice()
    }

    override fun onDeleteCartListResult(result: Boolean) {
        refreshEditStatus()
        loadData()
    }

    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM).withInt(ProviderConstant.KEY_ORDER_ID, result).navigation()
    }

    /**
     * 全选反选监听  价格联动监听
     */
    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe { t: CartAllCheckedEvent ->
                    run {
                        mAllCheckedCb.isChecked = t.isAllChecked
                        updateTotalPrice()
                    }
                }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe { t: UpdateTotalPriceEvent ->
                    run {
                        updateTotalPrice()
                    }
                }
                .registerInBus(this)
    }

    private fun updateTotalPrice() {
        mTotalPrice = mCartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getBackIv().setVisible(isVisible)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



