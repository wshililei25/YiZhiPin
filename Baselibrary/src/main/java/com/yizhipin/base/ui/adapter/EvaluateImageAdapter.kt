package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.R
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_evaluate_image_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class EvaluateImageAdapter(val context: Context) : BaseRecyclerViewAdapter<String, EvaluateImageAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_evaluate_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mEvaluateItemIv.loadUrl(dataList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}