package com.yizhipin.usercenter.data.repository

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.data.api.MainApi
import com.yizhipin.data.response.Banner
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class MainRepository @Inject constructor() {

    /**
     * 获取用户信息
     */
    fun getBanner(): Observable<BaseResp<MutableList<Banner>>> {
        return RetrofitFactoryGet().create(MainApi::class.java)
                .getBanner()
    }

}