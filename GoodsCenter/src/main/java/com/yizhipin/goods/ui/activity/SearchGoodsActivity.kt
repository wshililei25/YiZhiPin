package com.yizhipin.goods.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseApplication.Companion.context
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.SearchKeyword
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.SearchPresenter
import com.yizhipin.goods.presenter.view.SearchView
import com.yizhipin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class SearchGoodsActivity : BaseMvpActivity<SearchPresenter>(), SearchView, BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener {

    @Autowired(name = GoodsConstant.KEY_GOODS_KEYWORD) //注解接收其他页面的传参
    @JvmField
    var mKeyword: String = ""

    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mOrder: String = "price"
    private var mOrderType: String = "asc"
    private var mSalesStatus = 0
    private var mPriceStatus = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)
        initView()
        initRefreshLayout()
        lodGoodsData()
    }

    private fun initView() {
        mAreaTv.isSelected = true
        mPriceTv.isSelected = true
        mSearchEt.setText(mKeyword)
        mCancelTv.onClick(this)

        mGoodsRv.layoutManager = LinearLayoutManager(this)
        mGoodsAdapter = GoodsAdapter(context!!, false)
        mGoodsRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id!!)
            }
        })

        mSearchEt.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() === KeyEvent.ACTION_UP) {
                    if (mSearchEt.getText().toString().isNullOrEmpty()) {
                    } else {
                        mKeyword = mSearchEt.getText().toString()
                        lodGoodsData()
                    }
                }
                return false
            }
        })
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        mRefreshLayout.setPullDownRefreshEnable(false) //禁止下拉刷新
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mCancelTv -> finish()
            R.id.mSalesView -> {
                mOrder = "sellCount"
                mSalesStatus++
                mPriceStatus = 0
                if (mSalesStatus > 2) {
                    mSalesStatus = 1
                }
                if (mSalesStatus == 1) {
                    mOrderType = "asc"
                }
                if (mSalesStatus == 2) {
                    mOrderType = "desc"
                }
                mSalesTv.isSelected = true
                mPriceTv.isSelected = false
                mSalesTv.setCompoundDrawables(null, null, getSortStatus(mSalesStatus), null)
                mPriceTv.setCompoundDrawables(null, null, getSortStatus(mPriceStatus), null)
                lodGoodsData()
            }
            R.id.mPriceView -> {
                mOrder = "price"
                mSalesStatus = 0
                mPriceStatus++
                if (mPriceStatus > 2) {
                    mPriceStatus = 1
                }
                if (mPriceStatus == 1) {
                    mOrderType = "asc"
                }
                if (mPriceStatus == 2) {
                    mOrderType = "desc"
                }
                mSalesTv.isSelected = false
                mPriceTv.isSelected = true
                mSalesTv.setCompoundDrawables(null, null, getSortStatus(mSalesStatus), null)
                mPriceTv.setCompoundDrawables(null, null, getSortStatus(mPriceStatus), null)
                lodGoodsData()
            }
        }
    }

    private fun getSortStatus(chooseStatus: Int): Drawable? {
        var drawable: Drawable? = null
        when (chooseStatus) {
            0 -> {
                drawable = ContextCompat.getDrawable(this!!, R.drawable.sort2)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
            1 -> {
                drawable = ContextCompat.getDrawable(this!!, R.drawable.sort)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
            2 -> {
                drawable = ContextCompat.getDrawable(this!!, R.drawable.sort3)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
        }
        return drawable
    }

    /**
     * 获取商品列表
     */
    private fun lodGoodsData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("name", mKeyword)
        map.put("order", mOrder)
        map.put("orderType", mOrderType)

        mMultiStateView.startLoading()
        mBasePresenter.getSearchGoodsList(map)
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
            lodGoodsData()
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

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    override fun onGetSearchKeywordSuccess(result: MutableList<SearchKeyword>?) {
    }

}



