package com.yizhipin.goods.presenter

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.presenter.view.GoodsDetailView
import com.yizhipin.goods.service.impl.CartServiceImpl
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/*
    商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl
    @Inject
    lateinit var mCartServiceImpl: CartServiceImpl

    /*
        获取商品详情
     */
    fun getGoodsDetail(map: MutableMap<String, String>) {
        mView.showLoading()
        mGoodsServiceImpl.getGoodsDetail(map).execute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /*
        加入购物车
     */
    fun addCart(map: MutableMap<String, String>) {
        mView.showLoading()
        mCartServiceImpl.addCart(map).execute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onAddCartSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 收藏商品
     */
    fun collectGood(map: MutableMap<String, String>) {
        mView.showLoading()
        mCartServiceImpl.collectGood(map).execute(object : BaseSubscriber<GoodsCollect>(mView) {
            override fun onNext(t: GoodsCollect) {
                mView.onCollectGoodSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 最新评价
     */
    fun getEvaluateNew(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.getEvaluateNew(map).execute(object : BaseSubscriber<Evaluate>(mView) {
            override fun onNext(t: Evaluate) {
                mView.onGetEvaluateNewSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 最新体验报告
     */
    fun getReportNew(map: MutableMap<String, String>) {
        mView.showLoading()
        mGoodsServiceImpl.getReportNew(map).execute(object : BaseSubscriber<Report>(mView) {
            override fun onNext(t: Report) {
                mView.onGetReportNewSuccess(t)
            }
        }, mLifecycleProvider)

    }

    fun getCartCountData(map: MutableMap<String, String>) {
//        mView.showLoading()
        mGoodsServiceImpl.getCartCountData(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetCartCountSuccess(t)
            }
        }, mLifecycleProvider)

    }
}
