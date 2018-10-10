package com.yizhipin.base.data.protocol

import android.os.Parcelable

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class BaseResp<out T>(val code:String,val msg:String ,val data:T)