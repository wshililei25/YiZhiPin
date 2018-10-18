package com.yizhipin.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.data.response.Banner
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface MainApi {
    /**
     * 获取banner
     */
    @GET("${Api.BANNER}")
    fun getBanner(): Observable<BaseResp<MutableList<Banner>>>

    /**
     * 获取热门商品
     */
    @GET(Api.HOT_GOODS_LIST)
    fun getGoodsList(@Query("hot") hot: Boolean): Observable<BaseResp<MutableList<Goods>>>
}