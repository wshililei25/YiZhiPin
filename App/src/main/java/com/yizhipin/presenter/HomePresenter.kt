package com.yizhipin.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.data.response.Banner
import com.yizhipin.presenter.view.HomeView
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class HomePresenter @Inject constructor() : BasePresenter<HomeView>() {

    @Inject
    lateinit var mServiceImpl: MainServiceImpl

    fun getBanner() {
        mView.showLoading()
        mServiceImpl.getBanner().execute(object : BaseSubscriber<MutableList<Banner>>(mView) {
            override fun onNext(t: MutableList<Banner>) {
                mView.onGetBannerSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 获取热门商品
     */
    fun getGoodsList() {
//        mView.showLoading()
        mServiceImpl.getGoodsList()
                .execute(object : BaseSubscriber<MutableList<Goods>>(mView) {
                    override fun onNext(t: MutableList<Goods>) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}

