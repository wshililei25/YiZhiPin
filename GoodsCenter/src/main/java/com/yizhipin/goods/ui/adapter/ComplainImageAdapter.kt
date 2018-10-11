package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.event.ImageMoreEvent
import kotlinx.android.synthetic.main.layout_complain_image_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class ComplainImageAdapter(val context: Context) : BaseRecyclerViewAdapter<String, ComplainImageAdapter.ViewHolder>(context) {

    private val TYPE_CAMERA = 1
    private val TYPE_PICTURE = 2
    private var selectMax = 6

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_complain_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowAddItem(position)) {
            TYPE_CAMERA
        } else {
            TYPE_PICTURE
        }
    }

    override fun getItemCount(): Int {
        return if (dataList.size < selectMax) {
            dataList.size + 1
        } else {
            dataList.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        //少于6张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            holder.itemView.mDelIv.setVisible(false)
            holder.itemView.mComplainItemIv.setImageResource(R.drawable.addaphoto)
            holder.itemView.mComplainItemIv.onClick {
                Bus.send(ImageMoreEvent())
            }
        } else {
            holder.itemView.mComplainItemIv.loadUrl(dataList[position])
            holder.itemView.mDelIv.setVisible(true)
            holder.itemView.mDelIv.onClick {
                val index = holder.getAdapterPosition()
                // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                if (index != RecyclerView.NO_POSITION) {
                    dataList.removeAt(index)
                    notifyItemRemoved(index)
                    notifyItemRangeChanged(index, dataList.size)
                }
            }
        }
    }

    private fun isShowAddItem(position: Int): Boolean {
        val size = if (dataList.size == 0) 0 else dataList.size
        return position == size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}