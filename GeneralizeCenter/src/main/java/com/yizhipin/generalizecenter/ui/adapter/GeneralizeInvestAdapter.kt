package com.yizhipin.generalizecenter.ui.adapter


import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import kotlinx.android.synthetic.main.layout_generalize_invest_item.view.*

class GeneralizeInvestAdapter(val context: Context) : BaseRecyclerViewAdapter<GeneralizeInvest, GeneralizeInvestAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_generalize_invest_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mGoodsNameTv.text = product.name
            holder.itemView.mRetailTv.text = amount
            holder.itemView.mShopTv.text = product.shop!!.shopName
            holder.itemView.mGoodsIv.loadUrl(product.imgurl!!)

            when (product.shop!!.shopIdentity) {
                "product" -> holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
                "homestay" -> holder.itemView.mTypeTv.text = context.getString(R.string.stay)
                "trip" -> holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
                "car" -> holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
            }
            when (status) {
                "1" -> {
                    holder.itemView.mStateTv.text = context.getString(R.string.earnings)
                    holder.itemView.mStateTv.setTextColor(ContextCompat.getColor(context, R.color.yMain))
                }
                "2" -> {
                    holder.itemView.mStateTv.text = context.getString(R.string.in_bidding)
                    holder.itemView.mStateTv.setTextColor(ContextCompat.getColor(context, R.color.yRed))
                }
                "3" -> {
                    holder.itemView.mStateTv.text = context.getString(R.string.finished)
                    holder.itemView.mStateTv.setTextColor(ContextCompat.getColor(context, R.color.yBlackGray))
                }
                "4" -> {
                    holder.itemView.mStateTv.text = context.getString(R.string.bidding_failure)
                    holder.itemView.mStateTv.setTextColor(ContextCompat.getColor(context, R.color.yBlackGray))
                }
            }
        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
