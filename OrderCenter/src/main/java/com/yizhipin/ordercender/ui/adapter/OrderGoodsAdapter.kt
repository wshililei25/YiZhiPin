package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.ordercender.R
import kotlinx.android.synthetic.main.layout_order_goods_item.view.*

/**
 * 订单详情页、订单列表中的商品
 */
class OrderGoodsAdapter(var context: Context) : BaseRecyclerViewAdapter<Goods, OrderGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_order_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mGoodsIv.loadUrl(model.imgurl)
        holder.itemView.mShopTv.text = model.shop.shopName
        holder.itemView.mAmountTv.text = context.getString(R.string.rmb).plus(model.price.toString())

        if (model.primaryCategory == "product") {
            holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
        } else if (model.primaryCategory == "homestay") {
            holder.itemView.mTypeTv.text = context.getString(R.string.stay)
        } else if (model.primaryCategory == "trip") {
            holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
        } else if (model.primaryCategory == "car") {
            holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
