package com.yizhipin.generalizecenter.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GeneralizeService {

    fun getGenBiddingList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<GeneralizeCollect>>>
    fun getGenBiddingDetails(map: MutableMap<String, String>): Observable<GeneralizeCollect>
    fun getGenGroupDetails(map: MutableMap<String, String>): Observable<GeneralizeGroupDetails>
    fun payPersonage(map: MutableMap<String, String>): Observable<String>
}
