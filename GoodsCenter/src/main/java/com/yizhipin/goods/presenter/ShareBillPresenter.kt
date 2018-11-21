package com.yizhipin.goods.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.ShareBill
import com.yizhipin.goods.presenter.view.ShareBillView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class ShareBillPresenter @Inject constructor() : BasePresenter<ShareBillView>() {

    @Inject
    lateinit var mServiceImpl: GoodsServiceImpl

    fun getShareBillList(map: MutableMap<String, String>) {

        mView.showLoading()
        mServiceImpl.getShareBillList(map)
                .execute(object : BaseSubscriber<MutableList<ShareBill>>(mView) {
                    override fun onNext(t: MutableList<ShareBill>) {
                        mView.onGetShareBillListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

}