package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.goods.data.api.GoodsApi
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.data.response.ShareBill
import io.reactivex.Observable
import javax.inject.Inject

/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        商品详情
     */
    fun getGoodsDetail(map: MutableMap<String, String>): Observable<BaseResp<Goods>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getGoodsDetail(map["id"]!!, map["loginUid"]!!)
    }

    fun getEvaluateNew(map: MutableMap<String, String>): Observable<BaseResp<Evaluate>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getEvaluateNew(map["pid"]!!)
    }

    fun getReportNew(map: MutableMap<String, String>): Observable<BaseResp<Report>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getReportNew(map["pid"]!!)
    }

    fun getCartCountData(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getCartCountData(map["uid"]!!)
    }

    fun getEvaluateList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        if (map["loginUid"].isNullOrEmpty()) {
            return RetrofitFactoryGet().create(GoodsApi::class.java).getEvaluateListNotLogin(map["currentPage"]!!, map["pid"]!!, map["shopId"]!!)
        } else {
            return RetrofitFactoryGet().create(GoodsApi::class.java).getEvaluateList(map["currentPage"]!!, map["pid"]!!, map["shopId"]!!, map["loginUid"]!!)
        }
    }

    fun getReportList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        if (map["loginUid"].isNullOrEmpty()) {
            return RetrofitFactoryGet().create(GoodsApi::class.java).getReportListNotLogin(map["currentPage"]!!, map["pid"]!!, map["shopId"]!!, map["uid"]!!)
        } else {
            return RetrofitFactoryGet().create(GoodsApi::class.java).getReportList(map["currentPage"]!!, map["pid"]!!, map["shopId"]!!, map["uid"]!!, map["loginUid"]!!)
        }
    }

    fun giveLike(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).giveLike()
    }

    fun giveLikeReport(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).giveLikeReport()
    }

    fun getShopDetails(map: MutableMap<String, String>): Observable<BaseResp<Shop>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getShopDetails(map["id"]!!, map["loginUid"]!!)
    }

    fun getUserDetails(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getUserDetails(map["id"]!!)
    }

    fun getCrowdorderList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<UserInfo>>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getCrowdorderList(map["uid"]!!)
    }

    fun getComplainShop(map: MutableMap<String, String>): Observable<BaseResp<Complain>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).getComplainShop()
    }

    fun collectShop(map: MutableMap<String, String>): Observable<BaseResp<Collect>> {
        return RetrofitFactoryPost(map).create(GoodsApi::class.java).collectShop()
    }

    fun getShareBillList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<ShareBill>>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java).getShareBillList(map["lng"]!!, map["lat"]!!, map["pid"]!!)
    }

}
