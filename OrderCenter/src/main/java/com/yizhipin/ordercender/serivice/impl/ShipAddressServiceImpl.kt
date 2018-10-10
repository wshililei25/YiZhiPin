package com.yizhipin.ordercender.serivice.impl

import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.ordercender.data.repository.ShipAddressRepository
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.serivice.ShipAddressService
import io.reactivex.Observable
import javax.inject.Inject

/*
    收货人信息 业务实现类
 */
class ShipAddressServiceImpl @Inject constructor(): ShipAddressService {

    @Inject
    lateinit var repository: ShipAddressRepository

    /*
        添加收货人信息
     */
    override fun addShipAddress(map:MutableMap<String,String>): Observable<ShipAddress> {
        return repository.addShipAddress(map).convert()

    }

    /*
        获取收货人信息列表
     */
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }

    /*
        修改收货人信息
     */
    override fun editShipAddress(map: MutableMap<String, String>): Observable<ShipAddress> {
        return  repository.editShipAddress(map).convert()
    }
    /*
        设置默认
     */
    override fun setDefaultShipAddress(map: MutableMap<String, String>): Observable<Boolean> {
        return  repository.setDefaultShipAddress(map).convertBoolean()
    }

    /*
        删除收货人信息
     */
    override fun deleteShipAddress(map: MutableMap<String, String>): Observable<Boolean> {
        return repository.deleteShipAddress(map).convertBoolean()
    }
}
