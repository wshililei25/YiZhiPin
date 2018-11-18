package com.yizhipin.goods.ui.fragment

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
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.event.LikeEvent
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.ReportPresenter
import com.yizhipin.goods.presenter.view.ReportView
import com.yizhipin.goods.ui.adapter.ReportAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by ${XiLei} on 2018/9/2.
 * 体验报告列表
 */
class ReportFragment : BaseMvpFragment<ReportPresenter>(), ReportView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private var mGoodsId: Int = 0
    private var mShopId: String = ""
    private var mUserId: String = ""
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private lateinit var mReportAdapter: ReportAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_recyclerview, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRefreshLayout()
        mMultiStateView.startLoading()
        initObserve()
    }

    private fun initView() {
        mGoodsId = activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, 0)
        arguments?.let {
            if (arguments!!.getString(GoodsConstant.KEY_SHOP_ID).isNullOrEmpty()) mShopId = "" else mShopId = arguments!!.getString(GoodsConstant.KEY_SHOP_ID)
            if (arguments!!.getString(GoodsConstant.KEY_USER_ID).isNullOrEmpty()) mUserId = "" else mUserId = arguments!!.getString(GoodsConstant.KEY_USER_ID)
        }
        mRv.layoutManager = LinearLayoutManager(activity)
        mReportAdapter = ReportAdapter(activity!!)
        mRv.adapter = mReportAdapter
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("pid", if (mShopId.isNullOrEmpty()) mGoodsId.toString() else "") //商品详情里的体验报告
        map.put("shopId", mShopId) //店铺的体验报告
        map.put("uid", mUserId) //用户的体验报告
        map.put("loginUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getReportList(map)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetEvaluateListSuccess(result: BasePagingResp<MutableList<Evaluate>>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.data != null && result.data!!.size > 0) {
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mReportAdapter.setData(result.data!!)
            } else {
                mReportAdapter.dataList.addAll(result.data!!)
                mReportAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }
    }

    override fun onDataIsNull() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    /**
     * 点赞 / 取消点赞
     */
    private fun initObserve() {
        Bus.observe<LikeEvent>()
                .subscribe { t: LikeEvent ->
                    run {
                        var map = mutableMapOf<String, String>()
                        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                        map.put("experienceId", t.evaId.toString())
                        mBasePresenter.giveLikeReport(map)
                    }
                }
                .registerInBus(this)

    }
}