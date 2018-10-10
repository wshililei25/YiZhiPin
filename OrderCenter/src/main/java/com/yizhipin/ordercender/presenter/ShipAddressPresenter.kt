package com.yizhipin.ordercender.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.presenter.view.ShipAddressView
import com.yizhipin.ordercender.serivice.impl.ShipAddressServiceImpl
import javax.inject.Inject

/*
    收货人列表Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var mShipAddressServiceImpl: ShipAddressServiceImpl

    /*
        获取收货人列表
     */
    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mShipAddressServiceImpl.getShipAddressList().execute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
        设置默认收货人信息
     */
    fun setDefaultShipAddress(map: MutableMap<String, String>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mShipAddressServiceImpl.setDefaultShipAddress(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
        删除收货人信息
     */
    fun deleteShipAddress(map: MutableMap<String, String>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mShipAddressServiceImpl.deleteShipAddress(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteResult(t)
            }
        }, mLifecycleProvider)

    }

}
