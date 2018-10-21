package com.yizhipin.usercenter.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.ui.activity.*
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: UserInfoActivity)
    fun inject(activity: BindMobileActivity)
    fun inject(activity: PayPwdSetActivity)
    fun inject(activity: PayPwdUpdateActivity)
    fun inject(activity: PayPwdResetActivity)
}