package com.yizhipin.ordercender.data.repository

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.net.RetrofitFactoryDelete
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.net.RetrofitFactoryPut
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.ordercender.data.api.ShipAddressApi
import com.yizhipin.ordercender.data.response.ShipAddress
import io.reactivex.Observable
import javax.inject.Inject


/*
   收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /*
        添加收货地址
     */
    fun addShipAddress(map: MutableMap<String, String>): Observable<BaseResp<ShipAddress>> {

        return RetrofitFactoryPost(map).create(ShipAddressApi::class.java).addShipAddress()
    }

    /*
        删除收货地址
     */
    fun deleteShipAddress(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryDelete(map).create(ShipAddressApi::class.java).deleteShipAddress(map.get("id")!!)
    }

    /*
        修改收货地址
     */
    fun editShipAddress(map: MutableMap<String, String>): Observable<BaseResp<ShipAddress>> {
        return RetrofitFactoryPut(map).create(ShipAddressApi::class.java).editShipAddress(map.get("id")!!)
    }

    /*
        设置默认
     */
    fun setDefaultShipAddress(map: MutableMap<String, String>): Observable<BaseResp<ShipAddress>> {
        return RetrofitFactoryPut(map).create(ShipAddressApi::class.java).setDefaultShipAddress(map.get("id")!!)
    }

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>> {

        return RetrofitFactoryGet().create(ShipAddressApi::class.java)
                .getShipAddressList(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))

    }

}
