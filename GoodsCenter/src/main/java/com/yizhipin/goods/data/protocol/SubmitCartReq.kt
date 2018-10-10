package com.yizhipin.goods.data.protocol

import com.yizhipin.goods.data.response.CartGoods

/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>, val totalPrice: Long)
