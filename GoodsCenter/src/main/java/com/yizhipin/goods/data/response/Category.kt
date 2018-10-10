package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
     商品分类
 */
@Parcelize
data class Category(
        val name: String,
        val cnName: String,
        val categorys: MutableList<CategorySecond>
) : Parcelable
