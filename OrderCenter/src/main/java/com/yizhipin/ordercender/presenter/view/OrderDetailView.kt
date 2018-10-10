package com.yizhipin.ordercender.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.ordercender.data.response.Order

/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
