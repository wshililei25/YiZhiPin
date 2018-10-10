package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
     商品分类
 */
@Parcelize
data class CategorySecond(
        val id: Int,
        val primaryCategory: String,
        val name: String,
        val ordernum: Int,
        var isSelected: Boolean = false//是否被选中
):Parcelable
