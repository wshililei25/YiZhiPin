package com.yizhipin.goods.data.protocol

import com.yizhipin.base.data.response.Goods

/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<Goods>, val totalPrice: Long)
