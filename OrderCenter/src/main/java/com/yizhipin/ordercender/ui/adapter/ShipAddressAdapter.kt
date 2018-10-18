package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.data.response.ShipAddress
import kotlinx.android.synthetic.main.layout_address_item.view.*

/*
    收货地址数据适配
 */
class ShipAddressAdapter(context: Context) : BaseRecyclerViewAdapter<ShipAddress, ShipAddressAdapter.ViewHolder>(context) {

    var mOptClickListener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mSetDefaultTv.isSelected = (isDefault == true)
            holder.itemView.mShipNameTv.text = name
            holder.itemView.mShipMobileTv.text = mobile
            holder.itemView.mShipAddressTv.text = pro + city + area + detail

            holder.itemView.mSetDefaultTv.onClick {
                mOptClickListener?.let {
                    if (holder.itemView.mSetDefaultTv.isSelected) {
                        return@onClick
                    }
                    isDefault = true
                    it.onSetDefault(this)
                }
            }

            holder.itemView.mEditTv.onClick {
                mOptClickListener?.let {
                    it.onEdit(this)
                }
            }
            holder.itemView.mDeleteTv.onClick {
                mOptClickListener?.let {
                    it.onDelete(this)
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /*
        对应操作接口
     */
    interface OnOptClickListener {
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address: ShipAddress)
        fun onDelete(address: ShipAddress)
    }
}
