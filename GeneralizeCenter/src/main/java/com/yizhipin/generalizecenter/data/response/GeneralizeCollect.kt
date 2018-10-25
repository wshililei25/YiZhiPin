package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralizeCollect(
        val id: Int,
        val uid: Int,
        val pid: Int,
        val beginTime: String,
        val endTime: String,
        val period: String,
        val getUid: String,
        val getGroupId: String,
        val promotion: String,
        val bidding: String,
        val amount: String,
        val max: String,
        val my: String,
        val groups: String,
        val product: Goods
) : Parcelable
