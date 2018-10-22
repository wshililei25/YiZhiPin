package com.yizhipin.ordercender.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.ordercender.data.protocol.CancelOrderReq
import com.yizhipin.ordercender.data.protocol.ConfirmOrderReq
import com.yizhipin.ordercender.data.protocol.GetOrderByIdReq
import com.yizhipin.ordercender.data.protocol.GetOrderListReq
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
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderReq): Observable<BaseResp<String>>

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

    /*
        根据ID获取订单
     */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdReq): Observable<BaseResp<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @GET(Api.ORDER_LIST)
    fun getOrderList(@Query("currentPage") currentPage: String, @Query("uid") uid: String
                     , @Query("status") status: String): Observable<BasePagingResp<MutableList<Order>>>

    /*
        提交订单
     */
    @POST(Api.SUBMIT_ORDER)
    fun submitOrder(): Observable<BaseResp<String>>

    /**
     * 优惠券列表
     */
    @POST(Api.COUPON_LIST)
    fun getCouponList(@Query("currentPage") currentPage:String,@Query("uid") uid:String): Observable<BasePagingResp<MutableList<Coupon>>>

}
