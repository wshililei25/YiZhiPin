package com.yizhipin.generalizecenter.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.generalizecenter.data.response.*
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GeneralizeService {

    fun getGenBiddingList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<GeneralizeCollect>>>
    fun getGenBiddingDetails(map: MutableMap<String, String>): Observable<GeneralizeCollect>
    fun getGenGroupDetails(map: MutableMap<String, String>): Observable<GeneralizeGroupDetails>
    fun payPersonage(map: MutableMap<String, String>): Observable<String>
    fun getInvestStatistics(map: MutableMap<String, String>): Observable<GeneralizeInvestAmount>
    fun getGenInvestList(map: MutableMap<String, String>): Observable<MutableList<GeneralizeInvest>>
    fun getInvestDetailsList(map: MutableMap<String, String>): Observable<MutableList<InvestList>>
    fun getInvestDetails(map: MutableMap<String, String>): Observable<InvestDetails>
    fun getEndTime(): Observable<String>
}
