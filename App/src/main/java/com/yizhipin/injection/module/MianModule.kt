package com.yizhipin.usercenter.injection.module

import com.yizhipin.usercenter.service.MainService
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class MianModule {

    @Provides
    fun providesUserService(service: MainServiceImpl): MainService {
        return service
    }
}