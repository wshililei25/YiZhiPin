package com.yizhipin.generalizecenter.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.InvestList
import kotlinx.android.synthetic.main.layout_invest_details_item.view.*

class InvestDetailsAdapter(val context: Context) : BaseRecyclerViewAdapter<InvestList, InvestDetailsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_generalize_invest_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mNameTv.text = "投资收益"
            holder.itemView.mDateTv.text = getTime
            holder.itemView.mAmountTv.text = getAmount
        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
