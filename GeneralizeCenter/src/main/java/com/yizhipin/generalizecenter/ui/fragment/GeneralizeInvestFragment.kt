package com.yizhipin.generalizecenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.data.response.InvestDetails
import com.yizhipin.generalizecenter.data.response.InvestList
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.generalizecenter.ui.activity.InvestDetailsActivity
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeInvestAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizeInvestPresenter
import kotlinx.android.synthetic.main.fragment_invest.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class GeneralizeInvestFragment : BaseMvpFragment<GeneralizeInvestPresenter>(), GeneralizeInvestView {

    private lateinit var mOrderAdapter: GeneralizeInvestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity!!)
        mOrderAdapter = GeneralizeInvestAdapter(activity!!)
        mOrderRv.adapter = mOrderAdapter
        mOrderAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GeneralizeInvest> {
            override fun onItemClick(item: GeneralizeInvest, position: Int) {
                Log.d("2","item.investmentId="+item.investmentId)
                activity!!.startActivity<InvestDetailsActivity>(GeneralizeConstant.KEY_INVEST_ID to item.investmentId)
            }

        })
    }

    private fun initRefreshLayout() {
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        map.put("status", arguments!!.getString(GeneralizeConstant.KEY_INVEST_STATUS, "-1").toString())
        mMultiStateView.startLoading()
        mBasePresenter.getGenInvestList(map)
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetInvestListSuccess(result: MutableList<GeneralizeInvest>) {
        if (result != null && result.size > 0) {
            mOrderAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onGetInvestAmountSuccess(result: GeneralizeInvestAmount) {
    }

    override fun onGetInvestDetailsListSuccess(result: MutableList<InvestList>) {
    }
    override fun onGetInvestDetailsSuccess(result: InvestDetails) {
    }
}