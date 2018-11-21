package com.yizhipin.goods.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.goods.injection.module.CartModule
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.ui.activity.ComplainActivity
import com.yizhipin.goods.ui.activity.GoodsDetailActivity
import com.yizhipin.goods.ui.activity.ShareBillListActivity
import com.yizhipin.goods.ui.fragment.EvaluateFragment
import com.yizhipin.goods.ui.fragment.ReportFragment
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {

    fun inject(activity: GoodsDetailActivity)
    fun inject(activity: EvaluateFragment)
    fun inject(activity: ReportFragment)
    fun inject(activity: ComplainActivity)
    fun inject(activity: ShareBillListActivity)
}