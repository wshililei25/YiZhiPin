package com.yizhipin.ordercender.data.repository


import com.yizhipin.base.data.net.RetrofitFactory
import com.yizhipin.base.data.net.RetrofitFactoryDelete
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.ordercender.data.api.OrderApi
import com.yizhipin.ordercender.data.protocol.ConfirmOrderReq
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import io.reactivex.Observable
import javax.inject.Inject

/*
   订单数据层
 */
class OrderRepository @Inject constructor() {

    /*
        取消订单
     */
    fun cancelOrder(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryDelete(map).create(OrderApi::class.java).cancelOrder(map["id"]!!)
    }

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).confirmOrder(ConfirmOrderReq(orderId))
    }


    fun getDefaultAddress(map: MutableMap<String, String>): Observable<BaseResp<ShipAddress>> {
        return RetrofitFactoryGet().create(OrderApi::class.java).getDefaultAddress(map["uid"]!!)
    }

    fun getOrderById(map:MutableMap<String,String>): Observable<BaseResp<Order>> {
        return RetrofitFactoryGet().create(OrderApi::class.java).getOrderById(map["id"]!!)
    }

    /*
        根据状态查询订单列表
     */
    fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Order>>> {
        return RetrofitFactoryGet().create(OrderApi::class.java).getOrderList(map["currentPage"]!!, map["uid"]!!, map["statusStr"]!!)
    }

    fun submitOrder(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryPost(map).create(OrderApi::class.java).submitOrder()
    }
    fun submitOrderReside(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryPost(map).create(OrderApi::class.java).submitOrderReside()
    }

    fun getCouponList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Coupon>>> {
        return RetrofitFactoryGet().create(OrderApi::class.java).getCouponList(map["currentPage"]!!, map["uid"]!!)
    }

}
