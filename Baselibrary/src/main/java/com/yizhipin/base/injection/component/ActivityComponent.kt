package com.yizhipin.base.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.yizhipin.base.injection.ActivityScope
import com.yizhipin.base.injection.moudule.ActivityModule
import com.yizhipin.base.injection.moudule.LifecycleProviderModule
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class)
        , modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun context(): Context
    fun activity(): Activity
    fun lifecycleProvider(): LifecycleProvider<*>
}