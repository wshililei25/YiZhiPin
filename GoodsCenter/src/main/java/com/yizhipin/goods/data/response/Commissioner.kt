package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    专员数据类
 */
@Parcelize
data class Commissioner(

        val id: String,
        val uid: String,
        val name: String,
        val idcard: String,
        val cardPre: String,
        val cardPost: String,
        val lng: String,
        val lat: String,
        val fullImage: String,
        val experienceCount: String,
        val status: String
): Parcelable

