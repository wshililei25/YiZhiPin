package com.yizhipin.base.injection.moudule

import com.trello.rxlifecycle2.LifecycleProvider
import com.yizhipin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/5.
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @ActivityScope
    @Provides
    fun provideLifecycle(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}