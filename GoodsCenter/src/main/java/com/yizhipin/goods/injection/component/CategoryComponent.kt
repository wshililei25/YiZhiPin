package com.yizhipin.goods.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.ui.fragment.CartFragment
import com.yizhipin.goods.ui.fragment.CategoryFragment
import com.yizhipin.goods.ui.fragment.CategorySecondFragment
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
}