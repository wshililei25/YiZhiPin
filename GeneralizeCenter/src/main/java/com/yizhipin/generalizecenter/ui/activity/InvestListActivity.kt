package com.yizhipin.generalizecenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.data.response.InvestDetails
import com.yizhipin.generalizecenter.data.response.InvestList
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.generalizecenter.ui.adapter.InvestDetailsAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizeInvestPresenter
import kotlinx.android.synthetic.main.activity_invest_list.*

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class InvestListActivity : BaseMvpActivity<GeneralizeInvestPresenter>(), GeneralizeInvestView {


    private lateinit var mOrderAdapter: InvestDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest_list)

        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
          mOrderRv.layoutManager = LinearLayoutManager(this!!)
          mOrderAdapter = InvestDetailsAdapter(this!!)
          mOrderRv.adapter = mOrderAdapter
    }

    private fun initRefreshLayout() {
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
          var map = mutableMapOf<String, String>()
          map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
          mMultiStateView.startLoading()
          mBasePresenter.getInvestDetailsList(map)
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetInvestDetailsListSuccess(result: MutableList<InvestList>) {
        if (result != null &&  result.size > 0) {
            mOrderAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onGetInvestListSuccess(result: MutableList<GeneralizeInvest>) {
    }

    override fun onGetInvestAmountSuccess(result: GeneralizeInvestAmount) {
    }
    override fun onGetInvestDetailsSuccess(result: InvestDetails) {
    }
}