package com.yizhipin.generalizecenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.DateUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
import com.yizhipin.generalizecenter.presenter.view.GeneralizeView
import com.yizhipin.generalizecenter.ui.activity.GeneralizeDetailsActivity
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeGoodsAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizePresenter
import kotlinx.android.synthetic.main.fragment_gen.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 * 推广中的商品列表
 */
class GeneralizeGoodsFragment : BaseMvpFragment<GeneralizePresenter>(), GeneralizeView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mGoodsAdapter: GeneralizeGoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_gen, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRefreshLayout()
        mMultiStateView.startLoading()
        loadData()
        if (arguments!!.getInt("position") == 0) {
            mTimeView.setVisible(true)
            loadEndTime()
        } else {
            mTimeView.setVisible(false)
        }

    }

    private fun initView() {

        mRv.layoutManager = LinearLayoutManager(activity)
        mGoodsAdapter = GeneralizeGoodsAdapter(context!!)
        mRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GeneralizeCollect> {
            override fun onItemClick(item: GeneralizeCollect, position: Int) {
                activity!!.startActivity<GeneralizeDetailsActivity>(GeneralizeConstant.KEY_GEN_ID to item.id)
            }
        })
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        mRefreshLayout.setPullDownRefreshEnable(false) //禁止下拉刷新
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取商品列表
     */
    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("bidding", "true")
        mBasePresenter.getGenBiddingList(map)
    }

    /**
     * 获取商品列表成功
     */
    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<GeneralizeCollect>>) {
        if (result != null && result.data != null && result.data.size > 0) {
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result.data)
            } else {
                mGoodsAdapter.dataList.addAll(result.data)
                mGoodsAdapter.notifyDataSetChanged()
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

    }

    /**
     * 获取倒计时
     */
    private fun loadEndTime() {
        mBasePresenter.getEndTime()
    }

    /**
     * 获取倒计时成功
     */
    override fun onGetEndTimeSuccess(result: String) {

        mTimeView.setTime(DateUtils.stringToLong(result, DateUtils.datePattern) - DateUtils.curTime)
        mTimeView.reStart()
    }

    override fun onGetGoodsDetailsSuccess(result: GeneralizeCollect) {
    }

    override fun onPayPersonageSuccess(result: String) {
    }

    override fun onGetGroupDetailsSuccess(result: GeneralizeGroupDetails) {
    }
}



