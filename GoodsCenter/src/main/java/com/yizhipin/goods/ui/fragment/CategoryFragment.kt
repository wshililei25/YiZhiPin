package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.event.HomeIntentEvent
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.CategoryPresenter
import com.yizhipin.goods.presenter.view.CategoryView
import com.yizhipin.goods.ui.activity.SearchActivity
import com.yizhipin.goods.ui.adapter.CategoryVpAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView, View.OnClickListener {

    private lateinit var mCategoryVpAdapter: CategoryVpAdapter
    private lateinit var mData: MutableList<Category>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mCategoryVpAdapter = CategoryVpAdapter(childFragmentManager)
        mVp.adapter = mCategoryVpAdapter
        mTab.setupWithViewPager(mVp)

        mSearchV.onClick(this)
        mSearchEt.onClick(this)
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

    private fun initObserve() {
        Bus.observe<HomeIntentEvent>()
                .subscribe { t: HomeIntentEvent ->
                    run {
                        when (t.position) {
                            0 -> mVp.setCurrentItem(0)
                            1 -> mVp.setCurrentItem(3)
                            2 -> mVp.setCurrentItem(1)
                            4 -> mVp.setCurrentItem(2)
                        }

                    }
                }.registerInBus(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSearchEt -> startActivity<SearchActivity>()
            R.id.mSearchV -> startActivity<SearchActivity>()
        }
    }

    override fun onGetCategorySencondSuccess(result: MutableList<CategorySecond>?) {
    }

    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<Goods>?>) {
    }

}



