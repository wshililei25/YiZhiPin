package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvestList(
        val amount: String,
        val count: String,
        val getAmount: String,
        val getTime: String,
        val groupId: String,
        val id: String,
        val investmentId: String,
        val per: String,
        val personId: String,
        val pid: String,
        val price: String,
        val uid: String

) : Parcelable
