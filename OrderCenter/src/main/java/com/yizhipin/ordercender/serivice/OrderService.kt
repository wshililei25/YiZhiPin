package com.yizhipin.ordercender.serivice

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import io.reactivex.Observable

/*
    订单业务 接口
 */
interface OrderService {

    fun getOrderById(map:MutableMap<String,String>): Observable<Order>


    fun submitOrder(map: MutableMap<String, String>): Observable<String>
    fun submitOrderReside(map: MutableMap<String, String>): Observable<String>

    /*
    根据状态查询订单列表
 */
    fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Order>>>

    /*
    取消订单
 */
    fun cancelOrder(map: MutableMap<String, String>): Observable<Boolean>

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>
    fun getDefaultAddress(map: MutableMap<String, String>): Observable<ShipAddress>
}
