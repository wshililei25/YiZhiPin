package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 投诉
 */
@Parcelize
data class Complain(
        val id: String,
        val uid: String,
        val shopId: String,
        val content: String,
        val imgurls: String,
        val process: Boolean,
        val createTime: String,
        val processTime: String
) : Parcelable


