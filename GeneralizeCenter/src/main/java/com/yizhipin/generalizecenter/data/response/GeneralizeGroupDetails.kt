package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeGroupDetails(
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
        val winner: Boolean,
        val personCount: String,
        val status: String,
        val users: MutableList<GeneralizeUsers>
) : Parcelable
