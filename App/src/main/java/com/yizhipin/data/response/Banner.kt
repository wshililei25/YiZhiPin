package com.yizhipin.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/10/4.
 */
@Parcelize
class Banner(var id: Int,
             var bannerImgurl: String,
             var pid: Int,
             var content: String,
             var href: String,
             var type: String):Parcelable