package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.ShopPresenter
import com.yizhipin.goods.presenter.view.ReportView
import com.yizhipin.goods.ui.adapter.CategoryVpAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺
 */
class ShopActivity : BaseMvpActivity<ShopPresenter>(), ReportView {

    private lateinit var mCategoryVpAdapter: CategoryVpAdapter
    private lateinit var mData: MutableList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_category)
        initView()
        loadData()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mCategoryVpAdapter = CategoryVpAdapter(supportFragmentManager)
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
//        mBasePresenter.getCategoryAll()
    }

    override fun onGetEvaluateListSuccess(result: BasePagingResp<MutableList<Evaluate>>) {

    }
    /*  override fun onGetCategoryAllSuccess(result: MutableList<Category>?) {
          mData = result!!
          mCategoryVpAdapter.setData(mData!!)
      }*/

}



