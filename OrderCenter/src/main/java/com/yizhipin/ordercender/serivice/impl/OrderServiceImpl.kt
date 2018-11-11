package com.yizhipin.ordercender.serivice.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.ordercender.data.repository.OrderRepository
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.serivice.OrderService
import io.reactivex.Observable
import javax.inject.Inject

/*
    订单业务实现类
 */
class OrderServiceImpl @Inject constructor() : OrderService {

    @Inject
    lateinit var repository: OrderRepository


    override fun getDefaultAddress(map: MutableMap<String, String>): Observable<ShipAddress> {
        return repository.getDefaultAddress(map).convert()
    }

    override fun getOrderById(map:MutableMap<String,String>): Observable<Order> {
        return repository.getOrderById(map).convert()
    }


    override fun submitOrder(map: MutableMap<String, String>): Observable<String> {
        return repository.submitOrder(map).convert()
    }

    override fun submitOrderReside(map: MutableMap<String, String>): Observable<String> {
        return repository.submitOrderReside(map).convert()
    }

    /*
        根据订单状态获取订单列表
     */
    override fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Order>>> {
        return repository.getOrderList(map).convertPaging()

    }

    /*
        取消订单
     */
    override fun cancelOrder(map: MutableMap<String, String>): Observable<Boolean> {
        return repository.cancelOrder(map).convertBoolean()
    }

    /*
        订单确认收货
     */
    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }
}
