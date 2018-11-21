package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.ShareBill
import kotlinx.android.synthetic.main.layout_share_bill_item.view.*

class ShareBillAdapter(val context: Context) : BaseRecyclerViewAdapter<ShareBill, ShareBillAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_share_bill_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mGoodsNameTv.text = commissioner.name
//            holder.itemView.mKmTv.text = commissioner.name
//            holder.itemView.mAddressTv.text = commissioner.name

        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
