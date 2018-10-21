package com.yizhipin.goods.data.response

import com.yizhipin.base.data.response.Goods

/*
    购物车商品数据类
 */
data class Cart(

        val shopName: String,
        val carts: MutableList<Goods>,
        var isSelected: Boolean = false
)

