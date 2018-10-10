package com.yizhipin.usercenter.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.BindMobileView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class BindMobilePresenter @Inject constructor() : BasePresenter<BindMobileView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    /**
     * 绑定手机号
     */
    fun bindMobile(map: MutableMap<String, String>) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mUserServiceImpl.bindMobile(map)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onBindMobileResult(t)
                    }
                }, mLifecycleProvider)

    }
}

