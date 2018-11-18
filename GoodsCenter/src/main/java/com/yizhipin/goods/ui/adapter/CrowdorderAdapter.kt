package com.yizhipin.goods.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R

class CrowdorderAdapter(val context: Context) : BaseRecyclerViewAdapter<UserInfo, CrowdorderAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_crowdorder_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

      /*  holder.itemView.mGoodsNameTv.text = model.name
        holder.itemView.mPinPriceTv.text = model.pinPrice.toString()
        holder.itemView.mPriceTv.text = model.price.toString()
        holder.itemView.mShopTv.text = model.shop!!.shopName

        if (!model.imgurl.isNullOrEmpty()) {
            holder.itemView.mGoodsIv.loadUrl(model.imgurl!!)
        }

        when (model.shop!!.shopIdentity) {
            "product" -> holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
            "homestay" -> holder.itemView.mTypeTv.text = context.getString(R.string.stay)
            "trip" -> holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
            "car" -> holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
        }*/

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
