package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    收藏数据类
 */
@Parcelize
data class Collect(
        val id: Int,
        val uid: Int,
        val shopId: Int,
        val shop: Shop
) : Parcelable


