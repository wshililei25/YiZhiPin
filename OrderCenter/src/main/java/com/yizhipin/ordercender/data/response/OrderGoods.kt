package com.yizhipin.ordercender.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

/*
   订单中的商品
 */
@Parcelize
data class OrderGoods(
        val id: String,
        val orderId: String,
        val pid: String,
        val count: String,
        val marketPrice: String,
        val price: String,
        val primaryCategory: String,
        val secondCategory: String,
        val shopId: String,
        val shopUid: String,
        val date: String,
        val product: Goods
): Parcelable

