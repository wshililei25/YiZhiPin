package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.event.SelectAddressEvent
import com.yizhipin.ordercender.presenter.OrderConfirmPresenter
import com.yizhipin.ordercender.presenter.view.OrderConfirmView
import com.yizhipin.ordercender.ui.adapter.OrderGoodsAdapter
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 订单详情
 */
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView, View.OnClickListener {

    private lateinit var mOrderGoodsAdapter: OrderGoodsAdapter
    private var mOrder: Order? = null
    private lateinit var mGoods: Goods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
        initObserve()
    }

    private fun initView() {

        mGoods = intent.getParcelableExtra("good_item")

        Log.d("2", "mGoods=" + mGoods)
        Log.d("2", "mGoods.name=" + mGoods?.name)

        /*       mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
               mOrderGoodsAdapter = OrderGoodsAdapter(this)
               mOrderGoodsRv.adapter = mOrderGoodsAdapter

               mSelectShipTv.onClick(this)
               mShipView.onClick(this)
       //        mSubmitOrderBtn.onClick(this)

               if (mOrderId == 0) mOrderView.setVisible(false) else mOrderView.setVisible(true)*/
    }

    private fun loadData() {
        /* var map = mutableMapOf<String, String>()
         map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
         mBasePresenter.getDefaultAddress(map)

         mBasePresenter.getOrderById(mOrderId)*/
    }

    override fun injectComponent() {
        /* DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
         mBasePresenter.mView = this*/
    }

    /**
     * 获取默认地址成功
     */
    override fun onGetDefaultAddressSuccess(result: ShipAddress) {

        updateAddressView(result)
    }

    override fun onGetOrderByIdResult(result: Order) {

        mOrder = result
//        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        mOrderGoodsAdapter.setData(result.orderGoodsList)

//        updateAddressView()
    }

    override fun onSubmitOrderResult(result: Boolean) {
        toast("提交成功")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSelectShipTv, R.id.mShipView -> {
                startActivity<ShipAddressActivity>()
            }
            /* R.id.mSubmitOrderBtn -> {
                 mOrder?.let {
                     mBasePresenter.submitOrder(mOrder!!)
                 }
             }*/
        }
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { t: SelectAddressEvent ->
                    run {
                        mOrder?.let {
                            updateAddressView(t.address)
                        }
                    }
                }.registerInBus(this)
    }

    private fun updateAddressView(result: ShipAddress) {
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

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}