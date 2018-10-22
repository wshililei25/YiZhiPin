package com.yizhipin.ordercender.common

/*
    订单状态
 */
class OrderStatus {
    companion object {
        const val ORDER_ALL = 0//全部
        const val ORDER_WAIT_PAY = 1//待支付
        const val ORDER_WAIT_SEND = 2//待发货
        const val ORDER_WAIT_CONFIRM = 3//待收货
        const val ORDER_WAIT_EVALUATE = 4//待评价
    }
}
