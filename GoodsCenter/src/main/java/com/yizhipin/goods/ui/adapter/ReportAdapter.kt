package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.DateUtils
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.event.LikeEvent
import com.yizhipin.provider.common.afterLogin
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
        holder.itemView.mPhoneReportTv.text = StringUtils.setMobileStar(modle.nickname)
        holder.itemView.mDateReportTv.text = DateUtils.parseDate(modle.createTime, DateUtils.FORMAT_SHORT)
        holder.itemView.mContentReportTv.text = modle.content
        holder.itemView.mLikeCountReportTv.text = "${context.getString(R.string.like)}${"("}${modle.zanCount}${")"}"
        holder.itemView.mEvaCountReportTv.text = "${context.getString(R.string.comment)}${"("}${modle.evaCount}${")"}"
        holder.itemView.mEvaluateReportStarView.setCheckStarCount(modle.starCount)
        holder.itemView.mUserIconReportIv.loadUrl(modle.imgurl)

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

        holder.itemView.mLikeCountReportTv.isSelected = modle.zan
        holder.itemView.mLikeCountReportTv.setCompoundDrawables(getSortStatus(modle.zan), null, null, null)
        holder.itemView.mLikeCountReportTv.onClick {
            afterLogin {
                if (modle.zan) {
                    modle.zanCount = modle.zanCount - 1
                } else {
                    modle.zanCount = modle.zanCount + 1
                }
                holder.itemView.mLikeCountReportTv.isSelected = !modle.zan
                holder.itemView.mLikeCountReportTv.setCompoundDrawables(getSortStatus(!modle.zan), null, null, null)
                modle.zan = !modle.zan
                notifyDataSetChanged()
                Bus.send(LikeEvent(modle.id))
            }
        }
    }

    private fun getSortStatus(isLike: Boolean): Drawable? {
        var drawable: Drawable? = null
        when (isLike) {
            true -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.like)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
            false -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.like2)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
        }
        return drawable
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}