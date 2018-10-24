package com.yizhipin.goods.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.CategoryPresenter
import com.yizhipin.goods.presenter.view.CategoryView
import com.yizhipin.goods.ui.activity.GoodsDetailActivity
import com.yizhipin.goods.ui.adapter.CategoryAdapter
import com.yizhipin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.fragment_category_second.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class CategorySecondFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView, BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener {


    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mCategory: Category //一级分类
    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mOrder: String = "price"
    private var mOrderType: String = "asc"
    private var mPosition: Int = 0
    private var mSalesStatus = 0
    private var mPriceStatus = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_second, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRefreshLayout()

        arguments?.let {
            mOrder = "price"
            mOrderType = "asc"
            val category = arguments!!.getParcelable<Category>(GoodsConstant.KEY_CATEGORY_ITEM)
            loadCategoryData(category)
        }
    }

    private fun initView() {

        mAreaTv.isSelected = true
        mPriceTv.isSelected = true
        mSalesView.onClick(this)
        mPriceView.onClick(this)
        //二级分类
        mTopCategoryRv.layoutManager = LinearLayoutManager(activity)
        mCategoryAdapter = CategoryAdapter(context!!)
        mTopCategoryRv.adapter = mCategoryAdapter
        mCategoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<CategorySecond> {
            override fun onItemClick(item: CategorySecond, position: Int) {
                mPosition = position
                for (category in mCategoryAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                mCategoryAdapter.notifyDataSetChanged()
                lodGoodsData(position)
            }
        })

        //商品
        mGoodsRv.layoutManager = LinearLayoutManager(activity)
        mGoodsAdapter = GoodsAdapter(context!!, false)
        mGoodsRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id!!)
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
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {
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
                lodGoodsData(mPosition)
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
                lodGoodsData(mPosition)
            }
        }
    }

    private fun getSortStatus(chooseStatus: Int): Drawable? {
        var drawable: Drawable? = null
        when (chooseStatus) {
            0 -> {
                drawable = ContextCompat.getDrawable(activity!!, R.drawable.sort2)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
            1 -> {
                drawable = ContextCompat.getDrawable(activity!!, R.drawable.sort)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
            2 -> {
                drawable = ContextCompat.getDrawable(activity!!, R.drawable.sort3)!!
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            }
        }
        return drawable
    }

    /**
     * 获取二级分类数据
     */
    fun loadCategoryData(category: Category) {
        mBasePresenter?.let {
            mCategory = category
            var map = mutableMapOf<String, String>()
            map.put("primaryCategory", category.name)
            mBasePresenter.getCategorySecond(map)
        }
    }

    /**
     * 获取二级分类成功
     */
    override fun onGetCategorySencondSuccess(result: MutableList<CategorySecond>?) {

        result?.let {
            result[0].isSelected = true
            mCategoryAdapter.setData(result)
            lodGoodsData(0)
        }
    }

    /**
     * 获取商品列表
     */
    private fun lodGoodsData(position: Int) {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("primaryCategory", mCategory.name)
        map.put("secondCategory", mCategoryAdapter.dataList[position].id.toString())
        map.put("order", mOrder)
        map.put("orderType", mOrderType)

        mMultiStateView.startLoading()
        mBasePresenter.getGoodsList(map)
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
            lodGoodsData(mPosition)
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

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



