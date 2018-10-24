package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectGoods(
        val id: Int? = 0,
        val uid: Int? = 0,
        val pid: Int? = 0,
        val product: Goods
) : Parcelable
