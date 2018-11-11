package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeCollectMax(
        val id: String,
        val name: String,
        val imgurl: String,
        val date: String,
        val amount: String,
        val type: String

) : Parcelable
