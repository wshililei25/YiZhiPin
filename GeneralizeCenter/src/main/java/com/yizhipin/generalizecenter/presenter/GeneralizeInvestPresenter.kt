package com.yizhipin.goods.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.data.response.InvestDetails
import com.yizhipin.generalizecenter.data.response.InvestList
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.generalizecenter.service.impl.GeneralizeServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GeneralizeInvestPresenter @Inject constructor() : BasePresenter<GeneralizeInvestView>() {

    @Inject
    lateinit var mCategoryServiceImpl: GeneralizeServiceImpl

    fun getInvestStatistics(map: MutableMap<String, String>) {

//        mView.showLoading()
        mCategoryServiceImpl.getInvestStatistics(map)
                .execute(object : BaseSubscriber<GeneralizeInvestAmount>(mView) {
                    override fun onNext(t: GeneralizeInvestAmount) {
                        mView.onGetInvestAmountSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getGenInvestList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getGenInvestList(map)
                .execute(object : BaseSubscriber<MutableList<GeneralizeInvest>>(mView) {
                    override fun onNext(t: MutableList<GeneralizeInvest>) {
                        mView.onGetInvestListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getInvestDetailsList(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getInvestDetailsList(map)
                .execute(object : BaseSubscriber<MutableList<InvestList>>(mView) {
                    override fun onNext(t: MutableList<InvestList>) {
                        mView.onGetInvestDetailsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getInvestDetails(map: MutableMap<String, String>) {

        mView.showLoading()
        mCategoryServiceImpl.getInvestDetails(map)
                .execute(object : BaseSubscriber<InvestDetails>(mView) {
                    override fun onNext(t: InvestDetails) {
                        mView.onGetInvestDetailsSuccess(t)
                    }
                }, mLifecycleProvider)

    }

}