package com.yizhipin.goods.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Goods
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/*
    商品数据适配器
 */
class GoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
//        holder.itemView.mGoodsIconIv.loadUrl(model.goodsDefaultIcon)

        holder.itemView.mGoodsNameTv.text = model.name
        holder.itemView.mSystemTv.text = model.pinPrice.toString()
        holder.itemView.mRetailTv.text = model.price.toString()
        holder.itemView.mShopTv.text = model.shop.shopName
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
