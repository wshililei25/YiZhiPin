package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.CategorySecond
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class CategoryAdapter(context: Context) : BaseRecyclerViewAdapter<CategorySecond, CategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val modle = dataList[position]
        holder.itemView.mTopCategoryNameTv.text = modle.name
        holder.itemView.mTopCategoryNameTv.isSelected = modle.isSelected
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}