package com.yizhipin.goods.injection.module

import com.yizhipin.generalizecenter.service.GeneralizeService
import com.yizhipin.generalizecenter.service.impl.GeneralizeServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class GeneralizeModule {

    @Provides
    fun provideCategoryService(service: GeneralizeServiceImpl): GeneralizeService {
        return service
    }
}