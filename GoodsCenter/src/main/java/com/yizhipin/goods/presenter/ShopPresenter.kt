package com.yizhipin.goods.presenter

import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.presenter.view.ShopView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * 店铺 Presenter
 */
class ShopPresenter @Inject constructor() : BasePresenter<ShopView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl

    /**
     * 店铺详情
     */
    fun getShopDetails(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.getShopDetails(map).execute(object : BaseSubscriber<Shop>(mView) {
            override fun onNext(t: Shop) {
                mView.onGetShopDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 举报投诉
     */
    fun getComplainShop(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.getComplainShop(map).execute(object : BaseSubscriber<Complain>(mView) {
            override fun onNext(t: Complain) {
                mView.onComplainShopSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 收藏店铺
     */
    fun collectShop(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.collectShop(map).execute(object : BaseSubscriber<Collect>(mView) {
            override fun onNext(t: Collect) {
                mView.oncollectShopSuccess(t)
            }
        }, mLifecycleProvider)
    }

}
