package com.yizhipin.provider.router

/*
    模块路由 路径定义
 */
object RouterPath {
    //用户模块
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
            const val SET_PAY_PWD = "/userCenter/setPayPwd" //设置支付密码
            const val UPDATE_PAY_PWD = "/userCenter/updatePayPwd" //修改支付密码
            const val RESET_PAY_PWD = "/userCenter/resetPayPwd" //重置支付密码
        }
    }

    //商品模块
    class GoodsCenter {
        companion object {
            const val PATH_GOODS_CART = "/goodsCenter/cart"
            const val PATH_GOODS_COLLECT = "/goodsCenter/collect"
        }
    }
    //订单模块
    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
            const val PATH_ORDER_DETAILS = "/orderCenter/details"
            const val PATH_ORDER_COUPON = "/orderCenter/coupon" //优惠券
        }
    }

    //支付模块
    class PayCenter {
        companion object {
            const val PATH_PAY_RECHARGE = "/payCenter/recharge"
            const val PATH_PAY_WITHDRAW = "/payCenter/withraw"
        }
    }

    //消息模块
    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/message/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }
}
