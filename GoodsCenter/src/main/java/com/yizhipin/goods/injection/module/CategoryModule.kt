package com.yizhipin.goods.injection.module

import com.yizhipin.goods.service.CategoryService
import com.yizhipin.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class CategoryModule {

    @Provides
    fun provideCategoryService(service: CategoryServiceImpl): CategoryService {
        return service
    }
}