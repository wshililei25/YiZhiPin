package com.yizhipin.base.data.response

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
        val imgurl: String,
        val name: String,
        val primaryCategory: String,
        val secondCategory: String,
        val count: Int,
        val price: Double,
        val pinPrice: Double,
        val hundredPrice: Double,
        val postage: Double,
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
        val order: String? = null,//为了解决使用Parcelable传参时参数为null报错的问题，这里允许参数为null(后台返null)
        val orderType: String? = null,
        val collection: Boolean = false,
        val shop: Shop,
        var goodsCount: Int = 1//购买的商品数量
) : Parcelable
