package com.yizhipin.ordercender.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.ordercender.data.response.Coupon

interface CouponView : BaseView {

    fun onCouponListSuccess(result: BasePagingResp<MutableList<Coupon>>)
}
