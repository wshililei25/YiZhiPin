package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.CategoryPresenter
import com.yizhipin.goods.presenter.view.CategoryView
import com.yizhipin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺的商品列表
 */
class GoodsFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private var mShopId: String = ""
    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_recyclerview, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRefreshLayout()
        mMultiStateView.startLoading()
        loadData()
    }

    private fun initView() {

        arguments?.let {
            mShopId = arguments!!.getString(GoodsConstant.KEY_SHOP_ID)
        }
        mRv.layoutManager = LinearLayoutManager(activity)
        mGoodsAdapter = GoodsAdapter(activity!!, true)
        mRv.adapter = mGoodsAdapter
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
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取商品列表
     */
    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("shopId", mShopId)
        mBasePresenter.getShopGoodsList(map)
    }

    /**
     * 获取商品列表成功
     */
    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<Goods>?>) {

        if (result != null && result.data != null && result.data!!.size > 0) {
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result.data!!)
            } else {
                mGoodsAdapter.dataList.addAll(result.data!!)
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

    override fun onGetCategoryAllSuccess(result: MutableList<Category>?) {
    }

    override fun onGetCategorySencondSuccess(result: MutableList<CategorySecond>?) {
    }
}



