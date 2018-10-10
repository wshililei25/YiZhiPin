package com.yizhipin.goods.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.presenter.view.ReportView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/*
    评价 Presenter
 */
class EvaluatePresenter @Inject constructor() : BasePresenter<ReportView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl

    /**
     * 评价
     */
    fun getEvaluateList(map :MutableMap<String,String>) {

        mView.showLoading()
        mGoodsServiceImpl.getEvaluateList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Evaluate>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Evaluate>>) {
                mView.onGetEvaluateListSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 点赞 / 取消点赞
     */
    fun giveLike(map :MutableMap<String,String>) {

        mGoodsServiceImpl.giveLike(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {

            }
        }, mLifecycleProvider)
    }

}
