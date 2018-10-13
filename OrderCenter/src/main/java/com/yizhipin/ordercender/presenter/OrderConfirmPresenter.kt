package com.yizhipin.ordercender.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.presenter.view.OrderConfirmView
import com.yizhipin.ordercender.serivice.impl.OrderServiceImpl
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var mOrderServiceImpl: OrderServiceImpl

    /**
     * 获取默认地址
     */
    fun getDefaultAddress(map: MutableMap<String, String>) {

        mView.showLoading()
        mOrderServiceImpl.getDefaultAddress(map).execute(object : BaseSubscriber<ShipAddress>(mView) {
            override fun onNext(t: ShipAddress) {
                mView.onGetDefaultAddressSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /*
        根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        mView.showLoading()
        mOrderServiceImpl.getOrderById(orderId).execute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
        提交订单
     */
    fun submitOrder(order: Order) {
        mView.showLoading()
        mOrderServiceImpl.submitOrder(order).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        }, mLifecycleProvider)

    }


}
