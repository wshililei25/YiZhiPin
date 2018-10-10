package com.yizhipin.goods.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 评论回复
 */
@Parcelize
data class EvaluateReply(
        val id: Int,
        val evaId: Int,
        val uid: Int,
        val nickname: String,
        val commmentNickname: String,
        val content: String,
        val commentId: Int,
        val createTime: String,
        val commentUid: Int

) : Parcelable
