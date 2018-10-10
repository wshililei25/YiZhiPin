package com.yizhipin.base.injection.moudule

import android.app.Activity
import com.yizhipin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}