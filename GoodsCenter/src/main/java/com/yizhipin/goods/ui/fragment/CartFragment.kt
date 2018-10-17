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
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.event.CartAllCheckedEvent
import com.yizhipin.base.event.CartDeleteAllEvent
import com.yizhipin.base.event.CartDeleteEvent
import com.yizhipin.base.event.UpdateTotalPriceEvent
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.BaseAlertDialog
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.injection.component.DaggerCartComponent
import com.yizhipin.goods.injection.module.CartModule
import com.yizhipin.goods.presenter.CartPresenter
import com.yizhipin.goods.presenter.view.CartView
import com.yizhipin.goods.ui.adapter.CartAdapter
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast


/**
 * Created by ${XiLei} on 2018/8/23.
 */
class CartFragment : BaseMvpFragment<CartPresenter>(), CartView, View.OnClickListener {

    private lateinit var mCartAdapter: CartAdapter

    private var mTotalPrice: Double = 0.00
    private var mPostage: Double = 0.00

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_cart, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        mCartRv.layoutManager = LinearLayoutManager(activity)
        mCartAdapter = CartAdapter(context!!)
        mCartRv.adapter = mCartAdapter

        mAllCheckedCb.onClick(this)
        mSettleAccountsBtn.onClick(this)
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
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getCartList(map)
    }

    /**
     * 获取购物车列表成功
     */
    override fun onGetCartListSuccess(result: MutableList<Cart>?) {
        if (result != null && result.size > 0) {
            mAllCheckedCb.isChecked = false
            mCartAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        updateTotalPrice()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mAllCheckedCb -> {
                for (item in mCartAdapter.dataList) {
                    item.isSelected = mAllCheckedCb.isChecked
                }
                mCartAdapter.notifyDataSetChanged()
            }
            R.id.mSettleAccountsBtn -> {
                val cartIdList: MutableList<Cart> = mutableListOf()
                mCartAdapter.dataList
                        .filter { it.isSelected }
                        .mapTo(cartIdList) { it }
                if (cartIdList.size == 0) {
                    toast("请选择需要提交的数据")
                } else {
//                mBasePresenter.submitCart(cartIdList, mTotalPrice)
                }
            }
        }
    }

    /**
     * 删除商品成功
     */
    override fun onDeleteCartListSuccess(result: Boolean) {

    }

    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_DETAILS).withInt(ProviderConstant.KEY_ORDER_ID, result).navigation()
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

        Bus.observe<CartDeleteEvent>()
                .subscribe { t: CartDeleteEvent ->
                    run {
                        var map = mutableMapOf<String, String>()
                        map.put("id", t.id.toString())
                        mBasePresenter.deleteCartList(map)
                    }
                }.registerInBus(this)

        Bus.observe<CartDeleteAllEvent>()
                .subscribe { t: CartDeleteAllEvent ->
                    run {
                        //当所有商品全部删除UI置空
                        if (mCartAdapter.dataList.size <= 0) {
                            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
                            mCartAdapter.notifyDataSetChanged()
                        }

                    }
                }.registerInBus(this)
    }

    /**
     * 选中更新总价和运费
     */
    private fun updateTotalPrice() {

        for (data in mCartAdapter.dataList) {
            mTotalPrice = data.carts
                    .filter { it.isSelected }
                    .map { it.count * it.price }
                    .sum()
            mPostage = data.carts
                    .filter { it.isSelected }
                    .map { it.postage }
                    .sum()
        }

        mTotalPriceTv.text = "¥  ${mTotalPrice}"
        mPostageTv.text = "(含运费${mPostage}元)"
    }

    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getBackIv().setVisible(isVisible)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



