package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    店铺数据类
 */
@Parcelize
data class Shop(
        val id: Int,
        val uid: Int,
        val shopIdentity: String,
        val shopName: String,
        val shopImgurl: String,
        val address: String,
        val contactUser: String,
        val city: String,
        val scope: String,
        val license: String,
        val recipients: String,
        val mobile: String,
        val backAddress: String,
        val backDetail: String,
        val status: Int
):Parcelable


