package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.LoginView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    fun login(map: MutableMap<String, String>) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mUserServiceImpl.login(map)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}