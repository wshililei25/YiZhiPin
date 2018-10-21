package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.event.CartCheckedEvent
import com.yizhipin.base.event.CartDeleteEvent
import com.yizhipin.base.event.UpdateCartSizeEvent
import com.yizhipin.base.event.UpdateTotalPriceEvent
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.BaseAlertDialog
import com.yizhipin.base.widgets.DefaultTextWatcher
import com.yizhipin.goods.R
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*


/**
 * 购物车二级适配器
 */
class CartGoodsAdapter(var context: Context) : BaseRecyclerViewAdapter<Goods, CartGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_cart_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        holder.itemView.mGoodsIconIv.loadUrl(model.productImgurl!!)
        holder.itemView.mGoodsNameTv.text = model.productName
        holder.itemView.mSinPriceTv.text = model.price.toString()
        holder.itemView.mPriceTv.text = "${model.price!! * model.count}"
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.count)

        if (position == dataList.size - 1) {
            holder.itemView.mLine.setVisible(false)
        } else {
            holder.itemView.mLine.setVisible(true)
        }

        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            //当所有的二级选中时发送事件让对应的一级选中
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all { it.isSelected }
            Bus.send(CartCheckedEvent(isAllChecked))
        }

        //删除按钮事件
        holder.itemView.mDeleteBtn.onClick {

            val baseAlertDialog = BaseAlertDialog(context!!)
            baseAlertDialog.setMessage(context.getString(R.string.delete_doods_confirm))
            baseAlertDialog.show()
            baseAlertDialog.setOkClickInterface(object : BaseAlertDialog.OkClickInterface {
                override fun okClickListener() {
                    dataList.removeAt(position)
                    notifyDataSetChanged()
                    Bus.send(CartDeleteEvent(model.id!!))
                }
            })
        }

        //商品数量变化监听
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //修复Bug，删除为空时异常
                if (s.isNullOrEmpty().not()) {
                    model.count = s.toString().toInt()
                    holder.itemView.mPriceTv.text = "${model.price!! * model.count}"
                    Bus.send(UpdateTotalPriceEvent())
                    Bus.send(UpdateCartSizeEvent(model.id.toString(), s.toString()))
                }
            }
        })

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
