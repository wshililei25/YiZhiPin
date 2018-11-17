package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.SearchKeyword
import com.yizhipin.goods.presenter.view.SearchView
import com.yizhipin.goods.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class SearchPresenter @Inject constructor() : BasePresenter<SearchView>() {

    @Inject
    lateinit var mCategoryServiceImpl: CategoryServiceImpl

    fun getSearchKeyword() {

        mCategoryServiceImpl.getSearchKeyword()
                .execute(object : BaseSubscriber<MutableList<SearchKeyword>?>(mView) {
                    override fun onNext(t: MutableList<SearchKeyword>?) {
                        mView.onGetSearchKeywordSuccess(t)
                    }
                }, mLifecycleProvider)

    }
    fun getSearchGoodsList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getSearchGoodsList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<Goods>?>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<Goods>?>) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}