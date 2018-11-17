package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
     商品分类
 */
@Parcelize
data class SearchKeyword(
        val id: String,
        val keyWords: String
) : Parcelable
