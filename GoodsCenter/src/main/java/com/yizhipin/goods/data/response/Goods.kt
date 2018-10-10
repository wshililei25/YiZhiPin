package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    商品数据类
 */
@Parcelize
data class Goods(
        val id: Int,
        val shopId: Int,
        val uid: Int,
        val name: String,
        val primaryCategory: String,
        val secondCategory: String,
        val count: Int,
        val price: Double,
        val pinPrice: Double,
        val hundredPrice: Double,
        val postage: Int,
        val banner: String,
        val content: String,
        val monthSellerCount: Int,
        val totalCount: Int,
        val evaCount: Int,
        val starCount: Int,
        val experienceCount: Int,
        val hot: Boolean,
        val province: String,
        val city: String,
        val area: String,
        val serviceRate: String,
        val rewardRate: String,
        val popularizeRate: String,
        val order: String,
        val orderType: String,
        val shop: Shop
) : Parcelable
