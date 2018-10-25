package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.presenter.view.GeneralizeView
import com.yizhipin.generalizecenter.service.impl.GeneralizeServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GeneralizePresenter @Inject constructor() : BasePresenter<GeneralizeView>() {

    @Inject
    lateinit var mCategoryServiceImpl: GeneralizeServiceImpl

    fun getGenBiddingList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getGenBiddingList(map)
                .execute(object : BaseSubscriber<BasePagingResp<MutableList<GeneralizeCollect>>>(mView) {
                    override fun onNext(t: BasePagingResp<MutableList<GeneralizeCollect>>) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}