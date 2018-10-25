package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralizeApi {

    /**
     * 正在竞价商品列表
     */
    @GET(Api.GENERALIZE_LIST)
    fun getGenBiddingList(@Query("currentPage") currentPage: String,@Query("bidding") bidding: String): Observable<BasePagingResp<MutableList<GeneralizeCollect>>>

}
