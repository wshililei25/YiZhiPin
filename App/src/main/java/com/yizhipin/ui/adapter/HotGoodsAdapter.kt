package com.yizhipin.ui.adapter


import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_goods_hot_item.view.*

/*
    商品数据适配器
 */
class HotGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, HotGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_hot_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mGoodsNameTv.text = model.name
        holder.itemView.mSystemTv.text = model.pinPrice.toString()
        holder.itemView.mRetailTv.text = model.price.toString()
        holder.itemView.mGoodsIv.loadUrl(model.imgurl!!)

        holder.itemView.retail.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.retail.paint.isAntiAlias = true
        holder.itemView.rmb.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.rmb.paint.isAntiAlias = true
        holder.itemView.mRetailTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.mRetailTv.paint.isAntiAlias = true
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
