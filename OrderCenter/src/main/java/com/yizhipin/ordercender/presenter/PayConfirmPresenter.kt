package com.yizhipin.ordercender.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.ordercender.serivice.impl.OrderServiceImpl
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class PayConfirmPresenter @Inject constructor() : BasePresenter<PayConfirmView>() {

    @Inject
    lateinit var mOrderServiceImpl: OrderServiceImpl

    /**
     * 提交订单
     */
    fun submitOrder(map: MutableMap<String, String>) {
        mView.showLoading()
        mOrderServiceImpl.submitOrder(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderSuccess(t)
            }
        }, mLifecycleProvider)

    }


}
