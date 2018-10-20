package com.yizhipin.ordercender.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress

/*
    订单确认页 视图回调
 */
interface OrderConfirmView : BaseView {

    //获取订单回调
    fun onGetOrderByIdResult(result: Order)
    //提交订单回调
    fun onSubmitOrderSuccess(result:Order)
    fun onGetDefaultAddressSuccess(result:ShipAddress)
}
