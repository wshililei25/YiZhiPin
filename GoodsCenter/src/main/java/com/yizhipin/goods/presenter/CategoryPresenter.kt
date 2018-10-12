package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.presenter.view.CategoryView
import com.yizhipin.goods.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var mCategoryServiceImpl: CategoryServiceImpl

    fun getCategoryAll() {

        mCategoryServiceImpl.getCategoryAll()
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        mView.onGetCategoryAllSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getCategorySecond(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getCategorySecond(map)
                .execute(object : BaseSubscriber<MutableList<CategorySecond>?>(mView) {
                    override fun onNext(t: MutableList<CategorySecond>?) {
                        mView.onGetCategorySencondSuccess(t)
                    }
                }, mLifecycleProvider)

    }
    fun getGoodsList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getGoodsList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<Goods>?>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<Goods>?>) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
    fun getShopGoodsList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getShopGoodsList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<Goods>?>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<Goods>?>) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}