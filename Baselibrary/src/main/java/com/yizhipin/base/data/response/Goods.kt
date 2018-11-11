package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    商品数据类
 */
@Parcelize
data class Goods(
        val id: Int? = 0,
        val shopId: Int? = 0,
        val uid: Int? = 0,
        val imgurl: String? = null,
        val name: String? = null,
        val primaryCategory: String? = null,
        val secondCategory: String? = null,
        var count: Int,
        val price: Double? = 0.0,
        val pinPrice: Double? = 0.0,
        val hundredPrice: Double? = 0.0,
        val postage: Double? = 0.0,
        val banner: String? = null,
        val content: String? = null,
        val monthSellerCount: Int,
        val totalCount: Int? = 0,
        val evaCount: Int? = 0,
        val starCount: Int? = 0,
        val experienceCount: Int? = 0,
        val hot: Boolean? = null,
        val province: String? = null,
        val city: String? = null,
        val area: String? = null,
        val serviceRate: String? = null,
        val rewardRate: String? = null,
        val popularizeRate: String? = null,
        val order: String? = null,//为了解决使用Parcelable传参时参数为null报错的问题，这里允许参数为null(后台返null)
        val orderType: String? = null,
        val collection: Boolean = false,
        val shop: Shop? = null,

        //从这往下是购物车list中相关的数据
        val productId: String? = null,
        val shopName: String? = null,
        val productName: String? = null,
        val productImgurl: String? = null,
        var isSelected: Boolean = false,
        var goodsCount: Int = 1, //购买的商品数量
        var startDate: String? = null, //一品小住起始时间
        var endDate: String? = null //一品小住结束时间
) : Parcelable
