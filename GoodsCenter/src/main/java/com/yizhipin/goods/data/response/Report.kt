package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 体验报告
 */
@Parcelize
data class Report(
        val id: Int,
        val uid: Int,
        val pid: Int,
        val shopId: Int,
        val shopUid: Int,
        val content: String,
        val imgurls: String,
        val evaCount: Int,
        val zanCount: Int,
        val createTime: String,
        val nickname: String,
        val imgurl: String,
        val comments: String,
        val zan: Boolean,
        val loginUid: String,
        val starCount: Int

) : Parcelable
