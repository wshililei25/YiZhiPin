package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CommissionerPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    /**
     * 获取用户信息
     */
    fun getUserInfo() {
        if (!checkNetWork())
            return

        mView.showLoading()
        mUserServiceImpl.getUserInfo().execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.getUserResult(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 编辑用户资料
     */
    fun editUserInfo(map: MutableMap<String, String>) {
        if (!checkNetWork())
            return

        mView.showLoading()
        mUserServiceImpl.editUserInfo(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, mLifecycleProvider)
    }

}

