package com.yizhipin.base.injection.moudule

import android.content.Context
import com.yizhipin.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}