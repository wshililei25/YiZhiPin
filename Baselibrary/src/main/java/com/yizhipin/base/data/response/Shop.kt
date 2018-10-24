package com.yizhipin.base.data.response

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
        val licenseAuth: Boolean,
        val recipients: String,
        val mobile: String,
        val backAddress: String,
        val backDetail: String,
        val status: Int,
        val totalSell: String? = null,
        val mouthSell: String? = null,
        val selfCon: String? = null,
        val bail: String,
        val creditScore: String,
        val starCount: Int,
        val collection : Boolean
) : Parcelable


