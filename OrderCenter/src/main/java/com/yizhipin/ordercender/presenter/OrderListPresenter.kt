package com.yizhipin.ordercender.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.presenter.view.OrderListView
import com.yizhipin.ordercender.serivice.impl.OrderServiceImpl
import javax.inject.Inject

/*
    订单列表Presenter
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var mOrderServiceImpl: OrderServiceImpl

    /*
        根据订单状态获取订单列表
     */
    fun getOrderList(map: MutableMap<String, String>) {

//        mView.showLoading()
        mOrderServiceImpl.getOrderList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Order>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Order>>) {
                mView.onGetOrderListResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mOrderServiceImpl.confirmOrder(orderId).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
        取消订单
     */
    fun cancelOrder(map: MutableMap<String, String>) {
        mView.showLoading()
        mOrderServiceImpl.cancelOrder(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, mLifecycleProvider)

    }


}
