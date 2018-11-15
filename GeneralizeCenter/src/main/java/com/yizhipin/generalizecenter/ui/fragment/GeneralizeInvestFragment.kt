package com.yizhipin.generalizecenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizeInvestPresenter
import kotlinx.android.synthetic.main.fragment_invest.*

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class GeneralizeInvestFragment : BaseMvpFragment<GeneralizeInvestPresenter>(), GeneralizeInvestView {
    override fun onGetInvestListSuccess(result: GeneralizeInvestAmount) {
    }


    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
//    private lateinit var mOrderAdapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView()
//        initRefreshLayout()
//        loadData()
    }

    private fun initView() {
        /*  mOrderRv.layoutManager = LinearLayoutManager(activity!!)
          mOrderAdapter = OrderAdapter(activity!!)
          mOrderRv.adapter = mOrderAdapter*/
    }

    private fun initRefreshLayout() {
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        /*  var map = mutableMapOf<String, String>()
          map.put("currentPage", mCurrentPage.toString())
          map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
          map.put("statusStr", arguments!!.getString(OrderConstant.KEY_ORDER_STATUS, "-1").toString())
          mMultiStateView.startLoading()
          mBasePresenter.getOrderList(map)*/
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /* override fun onGetOrderListResult(result: BasePagingResp<MutableList<Order>>) {

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

     }*/

}