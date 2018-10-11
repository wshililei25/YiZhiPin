package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.RetrofitFactory
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.goods.data.api.GoodsApi
import com.yizhipin.goods.data.protocol.GoodsReq
import com.yizhipin.goods.data.response.*
import io.reactivex.Observable
import javax.inject.Inject

/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {

        return null!!
//        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GoodsReq.GetGoodsListReq(categoryId, pageNo))
    }

    /*
        根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GoodsReq.GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /*
        商品详情
     */
    fun getGoodsDetail(map: MutableMap<String, String>): Observable<BaseResp<Goods>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getGoodsDetail(map["id"]!!)
    }

    fun getEvaluateNew(map: MutableMap<String, String>): Observable<BaseResp<Evaluate>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getEvaluateNew(map["pid"]!!)
    }

    fun getReportNew(map: MutableMap<String, String>): Observable<BaseResp<Report>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getReportNew(map["pid"]!!)
    }

    fun getEvaluateList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getEvaluateList(map["currentPage"]!!, map["pid"]!!,map["shopId"]!!, map["loginUid"]!!)
    }

    fun getReportList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getReportList(map["currentPage"]!!, map["pid"]!!,map["shopId"]!!, map["loginUid"]!!)
    }
    fun giveLike(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).giveLike()
    }
    fun giveLikeReport(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).giveLikeReport()
    }

    fun getShopDetails(map: MutableMap<String, String>): Observable<BaseResp<Shop>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getShopDetails(map["id"]!!)
    }
    fun getComplainShop(map: MutableMap<String, String>): Observable<BaseResp<Complain>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).getComplainShop()
    }

}
