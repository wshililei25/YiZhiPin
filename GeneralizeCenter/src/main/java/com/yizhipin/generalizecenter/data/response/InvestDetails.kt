package com.yizhipin.generalizecenter.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.Goods
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvestDetails(
        val id: Int,
        val status: String ? = null,
//        val investment: String,
        val product: Goods,
//        val incomeList: String,
        val max: GeneralizeCollectMax
) : Parcelable
