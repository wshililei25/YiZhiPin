package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.data.response.Coupon
import kotlinx.android.synthetic.main.layout_coupon_item.view.*

class CouponAdapter(context: Context) : BaseRecyclerViewAdapter<Coupon, CouponAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_coupon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mMoneyTv.text = "${amount}元"
            holder.itemView.mTypeTv.text = "通用类型: 满${minAmount}元可用"
            holder.itemView.mTimeTv.text = "过期时间: ${endTime}"
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}
