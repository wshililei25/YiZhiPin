package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.goods.data.protocol.GoodsReq
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.data.response.Report
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.*

/*
    商品接口
 */
interface GoodsApi {

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GoodsReq.GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /**
     * 商品列表
     */
    @GET(Api.GOODS_LIST)
    fun getGoodsList(@Query("currentPage") currentPage: String, @Query("primaryCategory") primaryCategory: String
                     , @Query("secondCategory") secondCategory: String, @Query("order") order: String
                     , @Query("orderType") orderType: String): Observable<BasePagingResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @GET("${Api.GOODS_DETAIL}${"/{id}"}")
    fun getGoodsDetail(@Path("id") id: String): Observable<BaseResp<Goods>>

    /**
     * 最新评价
     */
    @GET("${Api.EVALUATE_NEW}")
    fun getEvaluateNew(@Query("pid") pid: String): Observable<BaseResp<Evaluate>>

    /**
     * 最新体验报告
     */
    @GET("${Api.REPORT_NEW}")
    fun getReportNew(@Query("pid") pid: String): Observable<BaseResp<Report>>

    /**
     * 评价列表
     */
    @GET("${Api.EVALUATE_LIST}")
    fun getEvaluateList(@Query("currentPage") currentPage: String, @Query("pid") pid: String, @Query("loginUid") loginUid: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 体验报告列表
     */
    @GET("${Api.REPORT_LIST}")
    fun getReportList(@Query("currentPage") currentPage: String, @Query("pid") pid: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 点赞
     */
    @POST("${Api.GIVE_LIKE}")
    fun giveLike(): Observable<BaseResp<Boolean>>

}
