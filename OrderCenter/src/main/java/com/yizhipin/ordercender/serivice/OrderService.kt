package com.yizhipin.ordercender.serivice

import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import io.reactivex.Observable

/*
    订单业务 接口
 */
interface OrderService {

    /*
       根据ID查询订单
    */
    fun getOrderById(orderId: Int): Observable<Order>

    /*
    提交订单
 */
    fun submitOrder(map: MutableMap<String, String>): Observable<Boolean>

    /*
    根据状态查询订单列表
 */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

    /*
    取消订单
 */
    fun cancelOrder(orderId: Int): Observable<Boolean>

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>
    fun getDefaultAddress(map: MutableMap<String, String>): Observable<ShipAddress>
}
