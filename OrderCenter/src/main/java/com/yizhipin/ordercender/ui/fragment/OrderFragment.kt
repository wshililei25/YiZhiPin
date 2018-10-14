package com.yizhipin.ordercender.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.OrderListPresenter
import com.yizhipin.ordercender.presenter.view.OrderListView
import com.yizhipin.ordercender.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView, OrderAdapter.OnOptClickListener {

    private lateinit var mOrderAdapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }

    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mOrderAdapter = OrderAdapter(activity!!)
        mOrderRv.adapter = mOrderAdapter
        mOrderAdapter.listener = this

        /*
        列表单项点击事件
     */
        mOrderAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order> {
            override fun onItemClick(item: Order, position: Int) {
//                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mBasePresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mOrderAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货成功")
        loadData()
    }

    override fun onCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }

    override fun onOptClick(optType: Int, order: Order) {
        when (optType) {
            OrderConstant.OPT_ORDER_PAY -> {
               /* ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_RECHARGE)
                        .withInt(ProviderConstant.KEY_ORDER_ID, order.id)
                        .withLong(ProviderConstant.KEY_ORDER_PRICE, order.totalPrice)
                        .navigation()*/
            }
            OrderConstant.OPT_ORDER_CONFIRM -> {
//                mBasePresenter.confirmOrder(order.id)
            }
            OrderConstant.OPT_ORDER_CANCEL -> {
                //mPresenter.cancelOrder(order.id)
                showCancelDialog(order)
            }
        }
    }

    /*
      取消订单对话框
   */
    private fun showCancelDialog(order: Order) {
        AlertView("取消订单", "确定取消该订单？", "取消", null, arrayOf("确定"), activity, AlertView.Style.Alert, OnItemClickListener { o, position ->
            if (position == 0) {
//                mBasePresenter.cancelOrder(order.id)
            }
        }
        ).show()
    }

}