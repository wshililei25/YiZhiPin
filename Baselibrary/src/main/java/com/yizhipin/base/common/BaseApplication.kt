package com.yizhipin.base.common

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.injection.component.AppComponent
import com.yizhipin.base.injection.component.DaggerAppComponent
import com.yizhipin.base.injection.moudule.AppModule

/**
 * Created by ${XiLei} on 2018/8/4.
 */
open class BaseApplication : MultiDexApplication() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
        context = this
        app = this

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    private fun initAppInjection() {
        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context: Context
        lateinit var app: Application
    }
}