package com.yizhipin.usercenter.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.PayPwdView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class PayPwdPresenter @Inject constructor() : BasePresenter<PayPwdView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    /**
     * 设置支付密码
     */
    fun setPayPwd(map: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.setPayPwd(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetPayPwdSuccess(t)
            }
        }, mLifecycleProvider)

    }
    /**
     * 修改支付密码
     */
    fun updatePayPwd(map: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.updatePayPwd(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetPayPwdSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 重置支付密码
     */
    fun resetPayPwd(map: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.resetPayPwd(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetPayPwdSuccess(t)
            }
        }, mLifecycleProvider)

    }


}
