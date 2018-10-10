package com.yizhipin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.data.response.CategoryHome
import kotlinx.android.synthetic.main.layout_category_item.view.*

class CategoryHomeAdapter(context: Context) : BaseRecyclerViewAdapter<CategoryHome, CategoryHomeAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mTv.text = model.name
        holder.itemView.mIv.setImageResource(model.image)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
