package com.yizhipin.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.data.response.Banner
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface MainApi {

    @GET("${Api.BANNER}") //获取banner
    fun getBanner(): Observable<BaseResp<MutableList<Banner>>>

}