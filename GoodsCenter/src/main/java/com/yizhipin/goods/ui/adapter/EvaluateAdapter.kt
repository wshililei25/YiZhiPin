package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
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
import kotlinx.android.synthetic.main.layout_evaluate_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class EvaluateAdapter(var context: Context) : BaseRecyclerViewAdapter<Evaluate, EvaluateAdapter.ViewHolder>(context) {

    private lateinit var mEvaluateImageAdapter: EvaluateImageAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_evaluate_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val modle = dataList[position]

        holder.itemView.mLikeView.visibility = View.VISIBLE
        holder.itemView.mPhoneTv.text = modle.nickname.replaceRange(3, 7, "****")
        holder.itemView.mDateTv.text = DateUtils.parseDate(modle.createTime, DateUtils.FORMAT_SHORT).toString()
        holder.itemView.mContentTv.text = modle.content
        holder.itemView.mLikeCountTv.text = "${context.getString(R.string.like)}${"("}${modle.zanCount}${")"}"
        holder.itemView.mEvaCountTv.text = "${context.getString(R.string.like)}${"("}${modle.evaCount}${")"}"
        holder.itemView.mEvaluateStarView.setCheckStarCount(modle.starCount)
        GlideUtils.loadUrlImage(context, BaseConstant.IMAGE_SERVICE_ADDRESS.plus(modle.imgurl), holder.itemView.mUserIconIv)

        modle.imgurls?.let {
            holder.itemView.mImageRv.layoutManager = GridLayoutManager(context, 3)
            mEvaluateImageAdapter = EvaluateImageAdapter(context)
            holder.itemView.mImageRv.adapter = mEvaluateImageAdapter
            val list = modle.imgurls.split(",").toMutableList()
            mEvaluateImageAdapter.setData(list)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}