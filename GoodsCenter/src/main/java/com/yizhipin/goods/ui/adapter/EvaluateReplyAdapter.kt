package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.EvaluateReply
import kotlinx.android.synthetic.main.layout_evaluate_reply_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class EvaluateReplyAdapter(val context: Context) : BaseRecyclerViewAdapter<EvaluateReply, EvaluateReplyAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_evaluate_reply_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val modle = dataList[position]
        if (modle.commentId == 0) {
            val spannableString = SpannableString(modle.nickname + ":  " + modle.content)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#58bdaf")), 0, modle.nickname.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            holder.itemView.mEvaluateReplyTv.text = spannableString
        } else {
            val spannableString = SpannableString(modle.commmentNickname + "回复" + modle.nickname + ":  " + modle.content)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#58bdaf")), 0, modle.commmentNickname.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#58bdaf")), modle.commmentNickname.length + 2, modle.commmentNickname.length + 2 + modle.nickname.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            holder.itemView.mEvaluateReplyTv.text = spannableString
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}