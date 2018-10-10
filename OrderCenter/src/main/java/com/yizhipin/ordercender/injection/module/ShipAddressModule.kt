package com.yizhipin.ordercender.injection.module

import com.yizhipin.ordercender.serivice.ShipAddressService
import com.yizhipin.ordercender.serivice.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/*
    收货人信息Module
 */
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressservice(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }

}
