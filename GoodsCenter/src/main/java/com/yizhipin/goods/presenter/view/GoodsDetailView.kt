package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.data.response.Report

/*
    商品详情 视图回调
 */
interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailSuccess(result: Goods)
    //加入购物车
    fun onAddCartSuccess(result: Int)
    //最新评价
    fun onGetEvaluateNewSuccess(result: Evaluate)
    //最新体验报告
    fun onGetReportNewSuccess(result: Report)
}
