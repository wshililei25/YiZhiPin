package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 附近拼团
 */
@Parcelize
data class ShareBill(

        val hasCommissioner: Boolean,
        val commissioner: Commissioner
//        var tuans: Boolean = false
) : Parcelable

