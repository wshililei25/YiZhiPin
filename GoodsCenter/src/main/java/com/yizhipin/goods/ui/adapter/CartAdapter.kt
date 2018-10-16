package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.event.*
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Cart
import kotlinx.android.synthetic.main.layout_cart_item.view.*

/**
 * 购物车一级适配器
 */
class CartAdapter(val context: Context) : BaseRecyclerViewAdapter<Cart, CartAdapter.ViewHolder>(context) {

    private lateinit var mCartGoodsAdapter: CartGoodsAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mCartGoodsRv.layoutManager = LinearLayoutManager(context!!)
        mCartGoodsAdapter = CartGoodsAdapter(context!!)
        mCartGoodsAdapter.setData(model.carts)
        holder.itemView.mCartGoodsRv.adapter = mCartGoodsAdapter

        //是否选中
        holder.itemView.mShopCb.isChecked = model.isSelected
        holder.itemView.mShopTv.text = model.shopName

        //当一级选中时让所有二级全部选中
        for (item in mCartGoodsAdapter.dataList) {
            item.isSelected = holder.itemView.mShopCb.isChecked
        }
        Bus.send(UpdateTotalPriceEvent())

        //选中按钮事件
        holder.itemView.mShopCb.onClick {
            model.isSelected = holder.itemView.mShopCb.isChecked

            //当一级选中时让所有二级全部选中
            for (item in mCartGoodsAdapter.dataList) {
                item.isSelected = holder.itemView.mShopCb.isChecked
            }
            mCartGoodsAdapter.notifyDataSetChanged()
            //当所有的一级全部选中时发送事件让最外边的全部CheckBox选中
            val isAllChecked = dataList.all { it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
        }

        Bus.observe<CartCheckedEvent>()
                .subscribe { t: CartCheckedEvent ->
                    run {
                        //当所有的二级选中时让对应的一级选中
                        holder.itemView.mShopCb.isChecked = t.isAllChecked
                        model.isSelected = holder.itemView.mShopCb.isChecked
                        //当所有的一级全部选中时发送事件让最外边的全部CheckBox选中
                        val isAllChecked = dataList.all { it.isSelected }
                        Bus.send(CartAllCheckedEvent(isAllChecked))

                    }
                }.registerInBus(context)

        Bus.observe<CartDeleteEvent>()
                .subscribe { t: CartDeleteEvent ->
                    run {
                        //当所有的二级全部移除时让对应的一级移除
                        if (dataList.get(position).carts.size <= 0) {
                            dataList.removeAt(position)
                            notifyDataSetChanged()
                            Bus.send(CartDeleteAllEvent())
                        }

                    }
                }.registerInBus(context)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
