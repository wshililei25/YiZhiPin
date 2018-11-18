package com.yizhipin.goods.presenter

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.presenter.view.UserView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * 用户 Presenter
 */
class UserPresenter @Inject constructor() : BasePresenter<UserView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl

    fun getUserDetails(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.getUserDetails(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onGetUserDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }
    fun getCrowdorderList(map: MutableMap<String, String>) {

        mView.showLoading()
        mGoodsServiceImpl.getCrowdorderList(map).execute(object : BaseSubscriber<MutableList<UserInfo>>(mView) {
            override fun onNext(t: MutableList<UserInfo>) {
                mView.onGetCrowdorderListSuccess(t)
            }
        }, mLifecycleProvider)
    }

}
