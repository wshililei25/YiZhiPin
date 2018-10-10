package com.yizhipin.usercenter.service

import com.yizhipin.data.response.Banner
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface MainService {

    fun getBanner(): Observable<MutableList<Banner>>
}
