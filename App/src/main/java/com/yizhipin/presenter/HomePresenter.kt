package com.yizhipin.presenter

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
        if (!checkNetWork())
            return

        mView.showLoading()
        mServiceImpl.getBanner().execute(object : BaseSubscriber<MutableList<Banner>>(mView) {
            override fun onNext(t: MutableList<Banner>) {
                mView.onGetBannerSuccess(t)
            }
        }, mLifecycleProvider)
    }

}

