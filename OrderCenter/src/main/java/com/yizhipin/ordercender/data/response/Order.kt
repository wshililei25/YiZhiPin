package com.yizhipin.ordercender.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    订单数据类
 */
@Parcelize
data class Order(
        val id: String,
        val nickname: String,
        val imgurl: String,
        val uid: String,
        val shopId: String,
        val shopUid: String,
        val pid: String,
        val amount: String,
        val payAmount: String,
        val productCount: String,
        val price: String,
        val postage: String,
        val orderNum: String,
        val payTime: String,
        val payType: String,
        val orderType: String,
        val tuanId: String,
        val tuanUid: String,
        val status: String,
        val backType: String,
        val recevieName: String,
        val receiveMobile: String,
        val receivePro: String,
        val receiveCity: String,
        val receiveArea: String,
        val receiveDetail: String,
        val receiveCode: String,
        val receiveName: String,
        val receiveNo: String,
        val sendTime: String,
        val getTime: String,
        val backName: String,
        val backMobile: String,
        val backPro: String,
        val backCity: String,
        val backArea: String,
        val backDetail: String,
        val backCode: String,
        val backExpress: String,
        val backNo: String,
        val backApplicationTime: String,
        val backStatus: String,
        val backTime: String,
        val backSuccessTime: String,
        val backAmount: String,
        val backMark: String,
        val backImgurl: String,
        val valid: String,
        val conponId: String,
        val conponAmount: String,
        val createTime: String,
        val beginTime: String,
        val endTime: String,
        val primaryCategory: String,
        val secondCategory: String,
        val products: MutableList<OrderGoods>

) : Parcelable

