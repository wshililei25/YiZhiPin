package com.yizhipin.ordercender.serivice.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.ordercender.data.repository.OrderRepository
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.serivice.CouponService
import io.reactivex.Observable
import javax.inject.Inject

class CouponServiceImpl @Inject constructor() : CouponService {

    @Inject
    lateinit var repository: OrderRepository

    override fun getCouponList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Coupon>>> {
        return repository.getCouponList(map).convertPaging()

    }
}
