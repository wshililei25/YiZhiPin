package com.yizhipin.goods.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/*
    商品数据适配器
 */
class GoodsAdapter(val context: Context, var isShop: Boolean) : BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mGoodsNameTv.text = model.name
        holder.itemView.mSystemTv.text = model.pinPrice.toString()
        holder.itemView.mRetailTv.text = model.price.toString()
        holder.itemView.mShopTv.text = model.shop!!.shopName

        if (!model.imgurl.isNullOrEmpty()) {
            holder.itemView.mGoodsIv.loadUrl(model.imgurl!!)
        }

        when (model.shop!!.shopIdentity) {
            "product" -> holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
            "homestay" -> holder.itemView.mTypeTv.text = context.getString(R.string.stay)
            "trip" -> holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
            "car" -> holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
        }

        if(model.shop!!.shopIdentity == "homestay"){
            holder.itemView.system.text = context.getString(R.string.price_pin)
            holder.itemView.retail.text = context.getString(R.string.price_original)
        }

        if (isShop) holder.itemView.mTypeTv.setVisible(false) else holder.itemView.mTypeTv.setVisible(true)
        if (isShop) holder.itemView.mShopTv.setVisible(false) else holder.itemView.mShopTv.setVisible(true)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
