package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Evaluate

/*
    商品详情 视图回调
 */
interface ReportView : BaseView {

    //评价列表
    fun onGetEvaluateListSuccess(result: BasePagingResp<MutableList<Evaluate>>)

}
