package com.yizhipin.usercenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 * 用户实体类
 */
@Parcelize
class UploadImage(val code: String,
                  val msg: String,
                  val data: String):Parcelable