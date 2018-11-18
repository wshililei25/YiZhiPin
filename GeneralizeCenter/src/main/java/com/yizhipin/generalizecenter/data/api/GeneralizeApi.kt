package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.generalizecenter.data.response.*
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GeneralizeApi {

    /**
     * 正在竞价商品列表
     */
    @GET(Api.GENERALIZE_LIST)
    fun getGenBiddingList(@Query("currentPage") currentPage: String, @Query("bidding") bidding: String): Observable<BasePagingResp<MutableList<GeneralizeCollect>>>

    /**
     * 正在竞价商品详情
     */
    @GET("${Api.GENERALIZE_DETAILS}${"/{id}"}")
    fun getGenBiddingDetails(@Path("id") id: String, @Query("uid") bidding: String): Observable<BaseResp<GeneralizeCollect>>

    /**
     * 投资团详情
     */
    @GET(Api.GENERALIZE_GROUP_DETAILS)
    fun getGenGroupDetails(@Query("id") id: String): Observable<BaseResp<GeneralizeGroupDetails>>

    /**
     * 个人出价投资
     */
    @POST(Api.PAY_PERSONAGE)
    fun payPersonage(): Observable<BaseResp<String>>

    /**
     * 获取倒计时
     */
    @POST(Api.END_TIME)
    fun getEndTime(): Observable<BaseResp<String>>

    /**
     * 投资收益金额
     */
    @GET(Api.GENERALIZE_INVEST_AMOUNT)
    fun getInvestStatistics(@Query("uid") uid: String): Observable<BaseResp<GeneralizeInvestAmount>>

    /**
     * 投资推广列表
     */
    @GET(Api.GENERALIZE_INVEST_LIST)
    fun getGenInvestList(@Query("uid") uid: String, @Query("status") status: String): Observable<BaseResp<MutableList<GeneralizeInvest>>>

    /**
     * 投资明细列表
     */
    @GET(Api.INVEST_DETAILS_LIST)
    fun getInvestDetailsList(@Query("uid") uid: String): Observable<BaseResp<MutableList<InvestList>>>

    /**
     * 投资详情
     */
    @GET(Api.INVEST_DETAILS)
    fun getInvestDetails(@Query("uid") uid: String, @Query("investmentId") investmentId: String): Observable<BaseResp<InvestDetails>>


}
