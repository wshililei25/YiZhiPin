package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.SearchKeyword
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.SearchPresenter
import com.yizhipin.goods.presenter.view.SearchView
import com.yizhipin.goods.widget.SkuView
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity


/**
 * Created by ${XiLei} on 2018/8/23.
 */
class SearchActivity : BaseMvpActivity<SearchPresenter>(), SearchView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        loadSearchKeyword()
    }

    private fun initView() {

        mCancelTv.onClick(this)
        mSearchEt.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() === KeyEvent.ACTION_UP) {
                    if (mSearchEt.getText().toString().isNullOrEmpty()) {
                    } else {
                        startActivity<SearchGoodsActivity>(GoodsConstant.KEY_GOODS_KEYWORD to mSearchEt.getText().toString())
                    }
                }
                return false
            }
        })

    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mCancelTv -> finish()
        }
    }

    /**
     * 获取搜索搜索关键字
     */
    fun loadSearchKeyword() {
        mBasePresenter.getSearchKeyword()
    }

    /**
     * 获取搜索搜索关键字成功
     */
    override fun onGetSearchKeywordSuccess(result: MutableList<SearchKeyword>?) {

        val skuView = SkuView(this)
        var list = mutableListOf<String>()
        for (re in result!!) {
            list.add(re.keyWords)
        }
        skuView.setSkuData(list)
        mSkuView.addView(skuView)
    }

    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<Goods>?>) {
    }
}



