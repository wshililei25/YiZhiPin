package com.yizhipin.ordercender.presenter

import com.yizhipin.base.presenter.view.BaseView

interface PayConfirmView : BaseView {

    //提交订单回调
    fun onSubmitOrderSuccess(result:String)
}
