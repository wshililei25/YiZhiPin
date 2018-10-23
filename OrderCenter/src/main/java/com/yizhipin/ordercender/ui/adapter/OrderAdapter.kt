package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.BaseAlertDialog
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.data.response.OrderGoods
import com.yizhipin.ordercender.event.DeleteOrderEvent
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.layout_order_item.view.*

/*
    订单列表数据适配
 */
class OrderAdapter(val context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {

    private lateinit var mOrderGoodsAdapter: OrderGoodsAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_order_item, parent, false)
        return ViewHolder(view)
    }

    /*
        绑定数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        var mTotalCount = 0

        with(model) {

            holder.itemView.mGoodsRv.layoutManager = LinearLayoutManager(context!!)
            mOrderGoodsAdapter = OrderGoodsAdapter(context!!)
            mOrderGoodsAdapter.setData(products)
            holder.itemView.mGoodsRv.adapter = mOrderGoodsAdapter

            holder.itemView.mPriceTv.text = "${payAmount} (运费:  ¥${postage})"
            when (orderType) {
                "buy" -> holder.itemView.mIsPinTv.text = context.getString(R.string.order_original)
                "tuan" -> holder.itemView.mIsPinTv.text = context.getString(R.string.order_pin_tuan)
                "bai" -> holder.itemView.mIsPinTv.text = context.getString(R.string.order_pin_tuan)
            }

            when (status.toInt()) {
                1 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.for_paymen)
                    holder.itemView.mCancelBtn.setVisible(true)
                    holder.itemView.mPayBtn.setVisible(true)
                }
                2 -> holder.itemView.mStatusTv.text = context.getString(R.string.send_goods)
                3 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.take_goods)
                    holder.itemView.mLogisticsBtn.setVisible(true)
                    holder.itemView.mConfirmBtn.setVisible(true)
                }
                4 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.appraise)
                    holder.itemView.mEvaluateBtn.setVisible(true)
                }
//                5 -> holder.itemView.mStatusTv.
                6 -> holder.itemView.mStatusTv.text = context.getString(R.string.customer_serviceing)
//                7 -> holder.itemView.mStatusTv.text = context.getString(R.string.for_paymen)
//                8 -> holder.itemView.mStatusTv.text = context.getString(R.string.for_paymen)
                9 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.for_paymen)
                    holder.itemView.mCancelBtn.setVisible(true)
                    holder.itemView.mPayBtn.setVisible(true)
                }
                10 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.take_goods)
                    holder.itemView.mLogisticsBtn.setVisible(true)
                    holder.itemView.mConfirmBtn.setVisible(true)
                }
                11 -> {
                    holder.itemView.mStatusTv.text = context.getString(R.string.take_goods)
                    holder.itemView.mLogisticsBtn.setVisible(true)
                    holder.itemView.mConfirmBtn.setVisible(true)
                }
            }
        }


        /*  if (model.orderGoodsList.size == 1) {//单个商品
              holder.itemView.mSingleGoodsView.setVisible(true)
              holder.itemView.mMultiGoodsView.setVisible(false)//单个商品隐藏多个商品视图
              val orderGoods = model.orderGoodsList[0]
              holder.itemView.mGoodsIconIv.loadUrl(orderGoods.goodsIcon)//商品图标
              holder.itemView.mGoodsDescTv.text = orderGoods.goodsDesc//商品描述
              holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)//商品价格
              holder.itemView.mGoodsCountTv.text = "x${orderGoods.goodsCount}"//商品数量
              mTotalCount = orderGoods.goodsCount

          } else {//多个商品视图展示
              holder.itemView.mSingleGoodsView.setVisible(false)//多个商品隐藏单个商品视图
              holder.itemView.mMultiGoodsView.setVisible(true)
              holder.itemView.mMultiGoodsView.removeAllViews()
              for (orderGoods in model.orderGoodsList) {//动态添加商品视图
                  val imageView = ImageView(mContext)
                  val lp = ViewGroup.MarginLayoutParams(mContext.dip(60.0f), mContext.dip(60.0f))
                  lp.rightMargin = mContext.dip(15f)
                  imageView.layoutParams = lp
                  imageView.loadUrl(orderGoods.goodsIcon)

                  holder.itemView.mMultiGoodsView.addView(imageView)

                  mTotalCount += orderGoods.goodsCount
              }
          }*/

//        holder.itemView.mOrderInfoTv.text = "合计${mTotalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"


        /*  when (model.orderStatus) {//根据订单状态设置颜色、文案及对应操作按钮
              OrderStatus.ORDER_WAIT_PAY -> {
                  holder.itemView.mOrderStatusNameTv.text = "待支付"
                  holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_red))
                  setOptVisible(false, true, true, holder)
              }
              OrderStatus.ORDER_WAIT_CONFIRM -> {
                  holder.itemView.mOrderStatusNameTv.text = "待收货"
                  holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_blue))
                  setOptVisible(true, false, true, holder)
              }

              OrderStatus.ORDER_COMPLETED -> {
                  holder.itemView.mOrderStatusNameTv.text = "已完成"
                  holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_yellow))
                  setOptVisible(false, false, false, holder)
              }

              OrderStatus.ORDER_CANCELED -> {
                  holder.itemView.mOrderStatusNameTv.text = "已取消"
                  holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_gray))
                  setOptVisible(false, false, false, holder)
              }
          }*/

        //设置确认收货点击事件
        holder.itemView.mConfirmBtn.onClick {
            //            listener?.let {
            //                it.onOptClick(OrderConstant.OPT_ORDER_CONFIRM, model)
//            }
        }

        //设置支付订单点击事件
        holder.itemView.mPayBtn.onClick {
            //            listener?.let {
            //                it.onOptClick(OrderConstant.OPT_ORDER_PAY, model)
//            }
        }


        //设置取消订单点击事件
        holder.itemView.mCancelBtn.onClick {
            val baseAlertDialog = BaseAlertDialog(context)
            baseAlertDialog.setTitle("提示")
            baseAlertDialog.setMessage("确定取消该订单?")
            baseAlertDialog.show()
            baseAlertDialog.setOkClickInterface(object : BaseAlertDialog.OkClickInterface {
                override fun okClickListener() {
                    Bus.send(DeleteOrderEvent(model.id))
                }
            })

        }

        holder.itemView.mItemView.onClick {
            ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_DETAILS)
                    .withString(ProviderConstant.KEY_ORDER_ID, model.id)
                    .navigation()
        }

        mOrderGoodsAdapter.setOnItemClickListener(object :OnItemClickListener<OrderGoods>{
            override fun onItemClick(item: OrderGoods, position: Int) {
                ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_DETAILS)
                        .withString(ProviderConstant.KEY_ORDER_ID, model.id)
                        .navigation()
            }
        })

    }

    /*
        设置操作按钮显示或隐藏
     */
    private fun setOptVisible(confirmVisible: Boolean, waitPayVisible: Boolean, cancelVisible: Boolean, holder: ViewHolder) {
        holder.itemView.mConfirmBtn.setVisible(confirmVisible)
        holder.itemView.mPayBtn.setVisible(waitPayVisible)
        holder.itemView.mCancelBtn.setVisible(cancelVisible)

        if (confirmVisible or waitPayVisible or cancelVisible) {
            holder.itemView.mBottomView.setVisible(true)
        } else {
            holder.itemView.mBottomView.setVisible(false)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
