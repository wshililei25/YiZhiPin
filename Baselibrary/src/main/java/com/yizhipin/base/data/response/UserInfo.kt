package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 * 用户实体类
 */
@Parcelize
class UserInfo(val id: String,
               val mobile: String,
               val nickname: String,
               val imgurl: String,
               val level: Int,
               val payPwd: String,
               val amount: String,
               val totalAmount: String,
               val push: Boolean,
               val type: String,
               val commissioner: Boolean,
               val weixin: String,
               val alipay: String,
               val alipayName: String,
               val score: String,
               val registerTime: String,
               val lastLoginTime: String,
               val shopId: String,
               val newUser: Boolean,
               val evaCount: String,
               val experience: String,
               val pin: String,
               val baiPin: String
):Parcelable