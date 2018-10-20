package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.event.SelectAddressEvent
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.OrderConfirmPresenter
import com.yizhipin.ordercender.presenter.view.OrderConfirmView
import com.yizhipin.ordercender.ui.adapter.OrderGoodsAdapter
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_details.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 订单详情
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_DETAILS)
class OrderDetailsActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView, View.OnClickListener {

    @Autowired(name = BaseConstant.KEY_IS_PIN) //注解接收上个页面的传参
    @JvmField
    var mIsPin: Boolean = false
    @Autowired(name = BaseConstant.KEY_GOODS_LIST) //注解接收上个页面的传参
    @JvmField
    var mGoodsList: ArrayList<Goods>? = null

    private lateinit var mShipAddress: ShipAddress
    private lateinit var mOrderGoodsAdapter: OrderGoodsAdapter
    private var mOrder: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        initView()
        loadData()
        initObserve()
    }

    private fun initView() {

        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mOrderGoodsAdapter = OrderGoodsAdapter(this, mIsPin)
        mOrderGoodsAdapter.setData(mGoodsList as MutableList<Goods>)
        mOrderGoodsRv.adapter = mOrderGoodsAdapter

        if (mIsPin) {
            /* var amount = 0.00
             for (good in mGoodsList as MutableList<Goods>) {
                 amount += good.pinPrice
             }
             mAmountTv.text = getString(R.string.rmb).plus(amount.toString())*/
            mBtn.text = getString(R.string.participate_share_bill)
        } else {
            /* var amount = 0.00
             for (good in mGoodsList as MutableList<Goods>) {
                 amount += good.price
             }
             mAmountTv.text = getString(R.string.rmb).plus(amount.toString())*/
            mBtn.text = getString(R.string.commit_order)
        }

        /* var amount = 0.00
         for (good in mGoodsList as MutableList<Goods>) {
             amount += good.postage
         }
         mPostageTv.text = getString(R.string.rmb).plus(amount.toString())*/
        mBtn.onClick(this)
        mShipView.onClick(this)
        mSelectShipTv.onClick(this)

//               if (mOrderId == 0) mOrderView.setVisible(false) else mOrderView.setVisible(true)
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getDefaultAddress(map)

//         mBasePresenter.getOrderById(mOrderId)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取默认地址成功
     */
    override fun onGetDefaultAddressSuccess(result: ShipAddress) {

        updateAddressView(result)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mOrder = result
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSelectShipTv, R.id.mShipView -> {
                startActivity<ShipAddressActivity>()
            }
            R.id.mBtn -> {
                if (null == mShipAddress) {
                    toast("请选择收货地址")
                    return
                }

                startActivity<PayConfirmActivity>(OrderConstant.KEY_GOODS_LIST to mGoodsList)

                /*  var map = mutableMapOf<String, String>()
                  map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                  map.put("pid", mGoodsList!!.get(0).id.toString())
                  map.put("productCount", mGoodsCountBtn.number.toString())
                  map.put("conponId", "")
                  map.put("addressId", mShipAddress.id.toString())

                  mBasePresenter.submitOrder(map)*/
            }
        }
    }

    /**
     * 选择地址回调
     */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { t: SelectAddressEvent ->
                    run {
                        updateAddressView(t.address)
                    }
                }.registerInBus(this)
    }

    private fun updateAddressView(result: ShipAddress) {

        mShipAddress = result
        if (result == null) {
            mSelectShipTv.setVisible(true)
            mShipView.setVisible(false)
        } else {
            mSelectShipTv.setVisible(false)
            mShipView.setVisible(true)

            mShipNameTv.text = getString(R.string.consignee).plus(result.name)
            mShipMobileTv.text = StringUtils.setMobileStar(result.mobile)
            mShipAddressTv.text = result.pro + result.city + result.area + result.detail
        }
    }

    override fun onSubmitOrderSuccess(result: Order) {
     /*   result?.let {
            startActivity<PayConfirmActivity>(OrderConstant.KEY_ORDER to result)
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}