package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeUsers(
        val id: String,
        val uid: String,
        val nickname: String? = null,
        val imgurl: String? = null,
        val groupId: String,
        val investmentId: String,
        val amount: String,
        val payTime: String,
        val payType: String,
        val ordernum: String,
        val pay: Boolean,
        val winner: String,
        val createTime: String,
        val status: String,
        val pid: String

) : Parcelable
