package com.yizhipin.base.injection.component

import android.content.Context
import com.yizhipin.base.injection.moudule.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}