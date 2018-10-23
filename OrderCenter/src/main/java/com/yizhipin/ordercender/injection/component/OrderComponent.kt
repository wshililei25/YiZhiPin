package com.yizhipin.ordercender.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.ui.activity.CouponActivity
import com.yizhipin.ordercender.ui.activity.OrderConfirmActivity
import com.yizhipin.ordercender.ui.activity.OrderDetailsActivity
import com.yizhipin.ordercender.ui.activity.PayConfirmActivity
import com.yizhipin.ordercender.ui.fragment.OrderFragment
import dagger.Component

/**
 * Created by ${XiLei} on 2018/9/24.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {

    fun inject(activity: OrderConfirmActivity)
    fun inject(activity: OrderFragment)
    fun inject(activity: PayConfirmActivity)
    fun inject(activity: CouponActivity)
    fun inject(activity: OrderDetailsActivity)
}