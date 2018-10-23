package com.yizhipin.ordercender.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.event.DeleteOrderEvent
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
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mOrderAdapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRefreshLayout()
        loadData()
        initObserve()
    }

    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity!!)
        mOrderAdapter = OrderAdapter(activity!!)
        mOrderRv.adapter = mOrderAdapter
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        mRefreshLayout.setPullDownRefreshEnable(false) //禁止下拉刷新
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        map.put("statusStr", arguments!!.getString(OrderConstant.KEY_ORDER_STATUS, "-1").toString())
        mMultiStateView.startLoading()
        mBasePresenter.getOrderList(map)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetOrderListResult(result: BasePagingResp<MutableList<Order>>) {

        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.data != null && result.data!!.size > 0) {
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mOrderAdapter.setData(result.data!!)
            } else {
                mOrderAdapter.dataList.addAll(result.data!!)
                mOrderAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    /**
     * 加载更多
     */
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }
    }

    /**
     * 下拉刷新
     */
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货成功")
        loadData()
    }


    /* override fun onOptClick(optType: Int, order: Order) {
         when (optType) {
             OrderConstant.OPT_ORDER_PAY -> {
                 *//* ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_RECHARGE)
                         .withInt(ProviderConstant.KEY_ORDER_ID, order.id)
                         .withLong(ProviderConstant.KEY_ORDER_PRICE, order.totalPrice)
                         .navigation()*//*
            }
            OrderConstant.OPT_ORDER_CONFIRM -> {
//                mBasePresenter.confirmOrder(order.id)
            }
            OrderConstant.OPT_ORDER_CANCEL -> {
                //mPresenter.cancelOrder(order.id)
                showCancelDialog(order)
            }
        }
    }*/

    private fun initObserve() {
        Bus.observe<DeleteOrderEvent>()
                .subscribe { t: DeleteOrderEvent ->
                    run {
                        showCancelDialog(t.id)
                    }
                }.registerInBus(this)
    }

    /**
     * 取消订单对话框
     */
    private fun showCancelDialog(id: String) {
        var map = mutableMapOf<String, String>()
        map.put("id", id)
        mBasePresenter.cancelOrder(map)
    }

    /**
     * 取消订单成功
     */
    override fun onCancelOrderResult(result: Boolean) {
        loadData()
    }

}