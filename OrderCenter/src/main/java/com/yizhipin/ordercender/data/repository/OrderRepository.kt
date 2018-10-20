package com.yizhipin.ordercender.data.repository


import com.yizhipin.base.data.net.RetrofitFactory
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.ordercender.data.api.OrderApi
import com.yizhipin.ordercender.data.protocol.CancelOrderReq
import com.yizhipin.ordercender.data.protocol.ConfirmOrderReq
import com.yizhipin.ordercender.data.protocol.GetOrderByIdReq
import com.yizhipin.ordercender.data.protocol.GetOrderListReq
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
    fun cancelOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).cancelOrder(CancelOrderReq(orderId))
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
    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).getOrderById(GetOrderByIdReq(orderId))
    }

    /*
        根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResp<MutableList<Order>?>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).getOrderList(GetOrderListReq(orderStatus))
    }

    /*
        提交订单
     */
    fun submitOrder(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(OrderApi::class.java).submitOrder()
    }

}
