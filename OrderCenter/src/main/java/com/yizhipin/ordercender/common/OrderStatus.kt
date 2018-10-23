package com.yizhipin.ordercender.common

/*
    订单状态
 */
class OrderStatus {
    companion object {
        const val ORDER_ALL = ""//全部
        const val ORDER_WAIT_PAY = "1,9"//待付款
        const val ORDER_PIN = "9,10"//拼单中
        const val ORDER_WAIT_SEND = "2,11"//待发货
        const val ORDER_WAIT_CONFIRM = "3"//待收货
        const val ORDER_WAIT_EVALUATE = "4"//待评价
        const val ORDER_AFTER_SALE = "6,7"//售后
    }
}
