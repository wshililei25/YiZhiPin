package com.yizhipin.ordercender.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.presenter.view.CouponView
import com.yizhipin.ordercender.serivice.impl.CouponServiceImpl
import javax.inject.Inject

class CouponPresenter @Inject constructor() : BasePresenter<CouponView>() {

    @Inject
    lateinit var mCouponServiceImpl: CouponServiceImpl

    /**
     *优惠券列表
     */
    fun getCouponList(map: MutableMap<String, String>) {

//        mView.showLoading()
        mCouponServiceImpl.getCouponList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Coupon>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Coupon>>) {
                mView.onCouponListSuccess(t)
            }
        }, mLifecycleProvider)

    }

}
