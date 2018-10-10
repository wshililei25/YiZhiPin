package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.event.LikeEvent
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.EvaluatePresenter
import com.yizhipin.goods.presenter.view.ReportView
import com.yizhipin.goods.ui.adapter.EvaluateAdapter
import kotlinx.android.synthetic.main.activity_evaluate.*

/**
 * Created by ${XiLei} on 2018/9/2.
 * 评价列表
 */
class EvaluateActivity : BaseMvpActivity<EvaluatePresenter>(), ReportView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @Autowired(name = GoodsConstant.KEY_GOODS_ID) //注解接收上个页面的传参
    @JvmField
    var mGoodsId: Int = 0
    @Autowired(name = GoodsConstant.KEY_EVA_COUNT) //注解接收上个页面的传参
    @JvmField
    var mEvaCount: Int = 0

    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private lateinit var mEvaluateAdapter: EvaluateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate)
        initView()
        initRefreshLayout()
        mMultiStateView.startLoading()
        loadData()
        initObserve()
    }

    private fun initView() {
        mRv.layoutManager = LinearLayoutManager(this)
        mEvaluateAdapter = EvaluateAdapter(this)
        mRv.adapter = mEvaluateAdapter

        /*  mEvaluateAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Evaluate> {
              override fun onItemClick(item: Evaluate, position: Int) {
                  startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
              }
          })*/
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("pid", mGoodsId.toString())
        map.put("loginUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getEvaluateList(map)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetEvaluateListSuccess(result: BasePagingResp<MutableList<Evaluate>>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.data != null && result.data!!.size > 0) {
            mHeaderBar.getTiTleTv().text = "所有评价(${mEvaCount})"
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mEvaluateAdapter.setData(result.data!!)
            } else {
                mEvaluateAdapter.dataList.addAll(result.data!!)
                mEvaluateAdapter.notifyDataSetChanged()
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
                        map.put("evaId", t.evaId.toString())
                        mBasePresenter.giveLike(map)
                    }
                }
                .registerInBus(this)

    }
}