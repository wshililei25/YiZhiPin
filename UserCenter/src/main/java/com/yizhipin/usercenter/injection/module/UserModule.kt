package com.yizhipin.usercenter.injection.module

import com.yizhipin.usercenter.service.UserService
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}