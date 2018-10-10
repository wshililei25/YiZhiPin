package com.yizhipin.ordercender.serivice

import com.yizhipin.ordercender.data.response.ShipAddress
import io.reactivex.Observable

/*
    收货人信息 业务接口
 */
interface ShipAddressService {

    /*
        添加收货地址
     */
    fun addShipAddress(map: MutableMap<String, String>): Observable<ShipAddress>

    /*
    获取收货地址列表
 */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    /*
     修改收货地址
  */
    fun editShipAddress(map: MutableMap<String, String>): Observable<ShipAddress>
    /*
    设置默认
  */
    fun setDefaultShipAddress(map: MutableMap<String, String>): Observable<Boolean>

    /*
    删除收货地址
 */
    fun deleteShipAddress(map: MutableMap<String, String>): Observable<Boolean>

}
