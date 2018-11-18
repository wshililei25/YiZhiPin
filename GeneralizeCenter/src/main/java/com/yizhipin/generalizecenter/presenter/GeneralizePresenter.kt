package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
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

    fun getGenBiddingDetails(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getGenBiddingDetails(map)
                .execute(object : BaseSubscriber<GeneralizeCollect>(mView) {
                    override fun onNext(t: GeneralizeCollect) {
                        mView.onGetGoodsDetailsSuccess(t)
                    }
                }, mLifecycleProvider)

    }
    fun getGenGroupDetails(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getGenGroupDetails(map)
                .execute(object : BaseSubscriber<GeneralizeGroupDetails>(mView) {
                    override fun onNext(t: GeneralizeGroupDetails) {
                        mView.onGetGroupDetailsSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun payPersonage(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.payPersonage(map)
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onPayPersonageSuccess(t)
                    }
                }, mLifecycleProvider)

    }
    fun getEndTime() {

//        mView.showLoading()
        mCategoryServiceImpl.getEndTime()
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetEndTimeSuccess(t)
                    }
                }, mLifecycleProvider)

    }
}