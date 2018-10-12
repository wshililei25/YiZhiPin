package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.CategoryPresenter
import com.yizhipin.goods.presenter.view.CategoryView
import com.yizhipin.goods.ui.adapter.CategoryVpAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private lateinit var mCategoryVpAdapter: CategoryVpAdapter
    private lateinit var mData: MutableList<Category>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mCategoryVpAdapter = CategoryVpAdapter(activity!!.supportFragmentManager)
        mVp.adapter = mCategoryVpAdapter
        mTab.setupWithViewPager(mVp)
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mBasePresenter.getCategoryAll()
    }

    override fun onGetCategoryAllSuccess(result: MutableList<Category>?) {
        mData = result!!
        mCategoryVpAdapter.setData(mData!!)
    }

    override fun onGetCategorySencondSuccess(result: MutableList<CategorySecond>?) {
    }

    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<Goods>?>) {
    }

}



