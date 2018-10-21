package com.yizhipin.ordercender.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 优惠券
 */
@Parcelize
data class Coupon(
        val id: Int,
        var uid: Int,
        var amount: String,
        var getTime: String,
        var endTime: String,
        var minAmount: String,
        var used: Boolean,
        var valid: Boolean,
        var useTime: Boolean
) : Parcelable
