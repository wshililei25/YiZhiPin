package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeInvest(
        val investmentId: String,
        val uid: String,
        val status: String,
        val amount: String,
        val type: String,
        val pid: String,
        val product: Goods

) : Parcelable
