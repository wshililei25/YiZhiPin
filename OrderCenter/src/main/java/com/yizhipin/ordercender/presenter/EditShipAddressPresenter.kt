package com.yizhipin.ordercender.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.presenter.view.EditShipAddressView
import com.yizhipin.ordercender.serivice.impl.ShipAddressServiceImpl
import javax.inject.Inject

/*
    编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var mShipAddressServiceImpl: ShipAddressServiceImpl

    /**
     * 新增收货地址
     */
    fun addShipAddress(map: MutableMap<String, String>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mShipAddressServiceImpl.addShipAddress(map).execute(object : BaseSubscriber<ShipAddress>(mView) {
            override fun onNext(t: ShipAddress) {
                mView.onAddShipAddressResult(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 修改收货地址
     */
    fun editShipAddress(map: MutableMap<String, String>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mShipAddressServiceImpl.editShipAddress(map).execute(object : BaseSubscriber<ShipAddress>(mView) {
            override fun onNext(t: ShipAddress) {
                mView.onEditShipAddressResult(t)
            }
        }, mLifecycleProvider)

    }


}
