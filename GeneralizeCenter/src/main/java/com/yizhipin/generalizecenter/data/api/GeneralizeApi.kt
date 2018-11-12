package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
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
     * 个人出价投资
     */
    @POST(Api.PAY_PERSONAGE)
    fun payPersonage(): Observable<BaseResp<String>>

}
