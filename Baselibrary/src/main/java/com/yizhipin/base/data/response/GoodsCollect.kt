package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    商品数据类
 */
@Parcelize
data class GoodsCollect(
        val id: Int,
        val uid: Int,
        val pid: Int,
        val product: Goods
) : Parcelable
