package com.yizhipin.goods.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

/*
    购物车商品数据类
 */
@Parcelize
data class Cart(

        val shopName: String,
        val carts: MutableList<Goods>,
        var isSelected: Boolean = false
): Parcelable

