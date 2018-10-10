package com.yizhipin.ordercender.injection.module

import com.yizhipin.ordercender.serivice.OrderService
import com.yizhipin.ordercender.serivice.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/9/24.
 */

@Module
class OrderModule {

    @Provides
    fun provideOrderService(orderServiceImpl: OrderServiceImpl): OrderService {
        return orderServiceImpl
    }
}