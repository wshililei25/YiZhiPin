package com.yizhipin.ordercender.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.ordercender.data.protocol.ConfirmOrderReq
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.*


/*
    订单 接口
 */
interface OrderApi {

    /*
        取消订单
     */
    @DELETE("${Api.ORDER_CANCEL}${"/{id}"}")
    fun cancelOrder(@Path("id") id: String): Observable<BaseResp<String>>

    /*
        确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderReq): Observable<BaseResp<String>>

    /**
     * 获取默认地址
     */
    @GET(Api.DEFAULT_ADDRESS.plus("/{uid}"))
    fun getDefaultAddress(@Path("uid") uid: String): Observable<BaseResp<ShipAddress>>

    /**
     * 订单详情
     */
    @GET("${Api.ORDER_CANCEL}${"/{id}"}")
    fun getOrderById(@Path("id") id: String): Observable<BaseResp<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @GET(Api.ORDER_LIST)
    fun getOrderList(@Query("currentPage") currentPage: String, @Query("uid") uid: String
                     , @Query("statusStr") status: String): Observable<BasePagingResp<MutableList<Order>>>

    /**
     * 提交订单
     */
    @POST(Api.SUBMIT_ORDER)
    fun submitOrder(): Observable<BaseResp<String>>

    /**
     * 提交订单(一品小住)
     */
    @POST(Api.SUBMIT_ORDER_RESIDE)
    fun submitOrderReside(): Observable<BaseResp<String>>

    /**
     * 优惠券列表
     */
    @POST(Api.COUPON_LIST)
    fun getCouponList(@Query("currentPage") currentPage: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<Coupon>>>

}
