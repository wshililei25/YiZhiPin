package com.yizhipin.ordercender.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.ordercender.injection.module.ShipAddressModule
import com.yizhipin.ordercender.ui.activity.ShipAddressActivity
import com.yizhipin.ordercender.ui.activity.ShipAddressEditActivity
import dagger.Component

/*
    收货人信息Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressEditActivity)
    fun inject(activity: ShipAddressActivity)
}
