package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.CollectGoods
import com.yizhipin.base.data.response.CollectShop
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.presenter.view.CollectView
import com.yizhipin.goods.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CollectPresenter @Inject constructor() : BasePresenter<CollectView>() {

    @Inject
    lateinit var mCategoryServiceImpl: CategoryServiceImpl

    fun getCollectGoodsList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getCollectGoodsList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<CollectGoods>?>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<CollectGoods>?>) {
                        mView.onGetCollectGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getCollectShopList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getCollectShopList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<CollectShop>?>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<CollectShop>?>) {
                        mView.onGetCollectShopListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}