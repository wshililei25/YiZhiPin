package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.presenter.view.ReportView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * 体验报告 Presenter
 */
class ReportPresenter @Inject constructor() : BasePresenter<ReportView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl

    /**
     * 体验报告
     */
    fun getReportList(map :MutableMap<String,String>) {

        mView.showLoading()
        mGoodsServiceImpl.getReportList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Evaluate>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Evaluate>>) {
                mView.onGetEvaluateListSuccess(t)
            }
        }, mLifecycleProvider)

    }
    /**
     * 点赞 / 取消点赞
     */
    fun giveLikeReport(map :MutableMap<String,String>) {

        mGoodsServiceImpl.giveLikeReport(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {

            }
        }, mLifecycleProvider)
    }

}
