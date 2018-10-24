package com.yizhipin.goods.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.CollectShop
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import kotlinx.android.synthetic.main.layout_collect_shop_item.view.*

/**
 * 收藏中的商品
 */
class CollectShopAdapter(val context: Context) : BaseRecyclerViewAdapter<CollectShop, CollectShopAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_collect_shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mGoodsNameTv.text = shop.shopName
            holder.itemView.mShopTv.text = "信用分 ${shop.creditScore}"
            holder.itemView.mGoodsIv.loadUrl(shop.shopImgurl!!)
            holder.itemView.mStarView.refreshView()
            holder.itemView.mStarView.setCheckStarCount(shop.starCount)
            when (shop.shopIdentity) {
                "product" -> holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
                "homestay" -> holder.itemView.mTypeTv.text = context.getString(R.string.stay)
                "trip" -> holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
                "car" -> holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
            }
        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
