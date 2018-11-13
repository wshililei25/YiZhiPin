package com.yizhipin.generalizecenter.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.GeneralizeUsers
import kotlinx.android.synthetic.main.layout_generalize_consortium_add_item.view.*

class GeneralizeUsersAdapter(val context: Context) : BaseRecyclerViewAdapter<GeneralizeUsers, GeneralizeUsersAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_generalize_consortium_add_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mMobileTv.text = nickname
            holder.itemView.mDateTv.text = "${createTime}最新出价"
            holder.itemView.mAmountTv.text = context.getString(R.string.rmb).plus(amount)
            if(!imgurl.isNullOrEmpty()){
                holder.itemView.mUserIconIv.loadUrl(imgurl!!)
            }
        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
