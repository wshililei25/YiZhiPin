package com.yizhipin.goods.injection.module

import com.yizhipin.goods.service.GoodsService
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodsService(service: GoodsServiceImpl): GoodsService {
        return service
    }
}