package com.yizhipin.goods.service.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.goods.data.repository.GoodsRepository
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var mRepository: GoodsRepository

    override fun getGoodsList(categoryId: Int,pageNo:Int): Observable<MutableList<Goods>?> {

        return mRepository.getGoodsList(categoryId, pageNo).convert()
    }

    /*
          根据关键字查询商品
       */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return mRepository.getGoodsListByKeyword(keyword,pageNo).convert()
    }

    /*
       获取商品详情
    */
    override fun getGoodsDetail(map :MutableMap<String,String>): Observable<Goods> {
        return mRepository.getGoodsDetail(map).convert()
    }

    override fun getEvaluateNew(map :MutableMap<String,String>): Observable<Evaluate> {
        return mRepository.getEvaluateNew(map).convert()
    }
    override fun getReportNew(map :MutableMap<String,String>): Observable<Report> {
        return mRepository.getReportNew(map).convert()
    }

    override fun getEvaluateList(map :MutableMap<String,String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        return mRepository.getEvaluateList(map).convertPaging()
    }
}