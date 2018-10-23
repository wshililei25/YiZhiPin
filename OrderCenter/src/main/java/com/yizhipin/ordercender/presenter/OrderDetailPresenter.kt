package com.yizhipin.ordercender.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.presenter.view.OrderDetailView
import com.yizhipin.ordercender.serivice.impl.OrderServiceImpl
import javax.inject.Inject

/*
    订单详情页Presenter
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var mOrderServiceImpl: OrderServiceImpl

    /**
     * 订单详情
     */
    fun getOrderById(map:MutableMap<String,String>) {
        mView.showLoading()
        mOrderServiceImpl.getOrderById(map).execute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, mLifecycleProvider)

    }
}
