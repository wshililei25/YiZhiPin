package com.yizhipin.ordercender.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
   收货地址
 */
@Parcelize
data class ShipAddress(
        val id: Int,
        var uid: Int,
        var pro: String,
        var city: String,
        var area: String,
        var name: String,
        var detail: String,
        var mobile: String,
        var isDefault: Boolean
) : Parcelable
