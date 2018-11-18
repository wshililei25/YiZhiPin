package com.yizhipin.goods.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.ui.activity.SearchActivity
import com.yizhipin.goods.ui.activity.SearchGoodsActivity
import com.yizhipin.goods.ui.activity.ShopActivity
import com.yizhipin.goods.ui.activity.UserActivity
import com.yizhipin.goods.ui.fragment.*
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CategoryModule::class))
interface CategoryComponent {

    fun inject(activity: CategoryFragment)
    fun inject(activity: CategorySecondFragment)
    fun inject(activity: CartFragment)
    fun inject(activity: ShopActivity)
    fun inject(activity: GoodsFragment)
    fun inject(activity: CollectGoodsFragment)
    fun inject(activity: CollectShopFragment)
    fun inject(activity: SearchActivity)
    fun inject(activity: SearchGoodsActivity)
    fun inject(activity: UserActivity)
    fun inject(activity: CrowdorderFragment)
}