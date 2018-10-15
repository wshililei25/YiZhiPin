package com.yizhipin.goods.data.response

/*
    购物车商品数据类
 */
data class Cart(

        val shopName: String,
        val carts: MutableList<CartGoods>,
        var isSelected: Boolean = false
)

