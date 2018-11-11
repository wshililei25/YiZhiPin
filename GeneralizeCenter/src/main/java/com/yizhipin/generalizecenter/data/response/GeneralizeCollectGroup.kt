package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeCollectGroup(
        val id: String,
        val pid: String,
        val investmentId: String,
        val nickname: String,
        val imgurl: String,
        val uid: String,
        val title: String,
        val content: String,
        val imgurls: String,
        val amount: String,
        val createTime: String,
        val winner: String,
        val personCount: String,
        val status: String

) : Parcelable
