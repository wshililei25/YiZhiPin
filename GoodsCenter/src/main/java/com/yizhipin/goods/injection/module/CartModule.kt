package com.yizhipin.goods.injection.module

import com.yizhipin.goods.service.CartService
import com.yizhipin.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
