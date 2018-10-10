package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.DateUtils
import com.yizhipin.base.utils.GlideUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Evaluate
import kotlinx.android.synthetic.main.layout_report_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class ReportAdapter(var context: Context) : BaseRecyclerViewAdapter<Evaluate, ReportAdapter.ViewHolder>(context) {

    private lateinit var mEvaluateImageAdapter: EvaluateImageAdapter
    private lateinit var mEvaluateReplyAdapter: EvaluateReplyAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_report_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val modle = dataList[position]

        holder.itemView.mLikeReportView.visibility = View.VISIBLE
        holder.itemView.mPhoneReportTv.text = modle.nickname.replaceRange(3, 7, "****")
        holder.itemView.mDateReportTv.text = DateUtils.parseDate(modle.createTime, DateUtils.FORMAT_SHORT).toString()
        holder.itemView.mContentReportTv.text = modle.content
        holder.itemView.mLikeCountReportTv.text = "${context.getString(R.string.like)}${"("}${modle.zanCount}${")"}"
        holder.itemView.mEvaCountReportTv.text = "${context.getString(R.string.comment)}${"("}${modle.evaCount}${")"}"
        holder.itemView.mEvaluateReportStarView.setCheckStarCount(modle.starCount)
        GlideUtils.loadUrlImage(context, BaseConstant.IMAGE_SERVICE_ADDRESS.plus(modle.imgurl), holder.itemView.mUserIconReportIv)

        if (!modle.imgurls.isNullOrEmpty()) {
            holder.itemView.mImageReportRv.visibility = View.VISIBLE
            holder.itemView.mImageReportRv.layoutManager = GridLayoutManager(context, 3)
            mEvaluateImageAdapter = EvaluateImageAdapter(context)
            holder.itemView.mImageReportRv.adapter = mEvaluateImageAdapter
            val list = modle.imgurls.split(",").toMutableList()
            mEvaluateImageAdapter.setData(list)
        }
        if (modle.comments.isNotEmpty()) {
            holder.itemView.mReplyReportRv.visibility = View.VISIBLE
            holder.itemView.mReplyReportRv.layoutManager = LinearLayoutManager(context)
            mEvaluateReplyAdapter = EvaluateReplyAdapter(context)
            holder.itemView.mReplyReportRv.adapter = mEvaluateReplyAdapter
            mEvaluateReplyAdapter.setData(modle.comments)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}