package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.widgets.DefaultTextWatcher
import com.yizhipin.base.widgets.NumberButton
import com.yizhipin.ordercender.R
import kotlinx.android.synthetic.main.layout_order_confirm_item.view.*
import org.jetbrains.anko.toast

/**
 * 订单详情页、订单列表中的商品
 */
class OrderConfirmAdapter(var context: Context, var mIsPin: Boolean) : BaseRecyclerViewAdapter<Goods, OrderConfirmAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_order_confirm_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mAmountTv.text = context.getString(R.string.rmb).plus(model.price.toString())
        holder.itemView.mPostageTv.text = context.getString(R.string.rmb).plus(model.postage.toString())
        if (model.name.isNullOrEmpty()) { //购物车
            holder.itemView.mGoodsNameTv.text = model.productName
            holder.itemView.mShopTv.text = model.shopName
            holder.itemView.mGoodsIv.loadUrl(model.productImgurl!!)

            model.goodsCount = model.count
            holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
            holder.itemView.mGoodsCountBtn.setBuyMax(model.count)
        } else { //单价买
            holder.itemView.mGoodsNameTv.text = model.name
            holder.itemView.mShopTv.text = model.shop!!.shopName

            if (!model.imgurl.isNullOrEmpty()) {
                holder.itemView.mGoodsIv.loadUrl(model.imgurl!!)
            }

            model.goodsCount = 1
            holder.itemView.mGoodsCountBtn.setBuyMax(model.count)
        }

        if (mIsPin) {
            holder.itemView.mPracticalAmountTv.text = context.getString(R.string.rmb).plus(model.pinPrice!! * model.goodsCount)
        } else {
            holder.itemView.mPracticalAmountTv.text = context.getString(R.string.rmb).plus(model.price!! * model.goodsCount)
        }

        if (model.primaryCategory == "homestay") {
            holder.itemView.mGoodsCountTv.text = "预订房间数量(剩余${model.count})"
            holder.itemView.mPostageView.setVisible(false)
            holder.itemView.mBookingDateView.setVisible(true)
        }

        holder.itemView.mGoodsCountBtn.setOnWarnListener(object : NumberButton.OnWarnListener {
            override fun onWarningForBuyMax(max: Int) {
                context.toast("不能大于库存")
            }

            override fun onWarningForInventory(inventory: Int) {

            }

        })
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //修复Bug，删除为空时异常
                if (s.isNullOrEmpty().not()) {
                    model.goodsCount = s.toString().toInt()

                    if (mIsPin) {
                        holder.itemView.mPracticalAmountTv.text = context.getString(R.string.rmb).plus(model.pinPrice!! * model.goodsCount)
                    } else {
                        holder.itemView.mPracticalAmountTv.text = context.getString(R.string.rmb).plus(model.price!! * model.goodsCount)
                    }
                }

            }
        })

        if (model.primaryCategory == "product") {
            holder.itemView.mTypeTv.text = context.getString(R.string.hamlet)
        } else if (model.primaryCategory == "homestay") {
            holder.itemView.mTypeTv.text = context.getString(R.string.stay)
        } else if (model.primaryCategory == "trip") {
            holder.itemView.mTypeTv.text = context.getString(R.string.group_group)
        } else if (model.primaryCategory == "car") {
            holder.itemView.mTypeTv.text = context.getString(R.string.motor_homes)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
