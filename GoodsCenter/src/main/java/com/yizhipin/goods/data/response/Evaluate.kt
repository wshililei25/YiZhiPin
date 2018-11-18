package com.yizhipin.goods.data.response

import android.os.Parcelable
import com.yizhipin.base.data.response.UserInfo
import kotlinx.android.parcel.Parcelize

/**
 * 评论
 */
@Parcelize
data class Evaluate(
        val id: Int,
        val shopId: Int,
        val uid: Int,
        val nickname: String,
        val imgurl: String,
        val level: Int,
        val oid: Int,
        val content: String,
        val imgurls: String,
        val starCount: Int,
        val createTime: String,
        var zanCount: Int,
        val evaCount: Int,
        var zan: Boolean,
        val loginUid: Int,
        val comments: MutableList<EvaluateReply>,
        val webUser: UserInfo
) : Parcelable
