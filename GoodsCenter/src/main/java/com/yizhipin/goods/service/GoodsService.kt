package com.yizhipin.goods.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.data.response.ShareBill
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GoodsService {

    fun getGoodsDetail(map: MutableMap<String, String>): Observable<Goods>
    fun getEvaluateNew(map: MutableMap<String, String>): Observable<Evaluate>
    fun getReportNew(map: MutableMap<String, String>): Observable<Report>
    fun getCartCountData(map: MutableMap<String, String>): Observable<String>
    fun getEvaluateList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>>
    fun getReportList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>>
    fun giveLike(map: MutableMap<String, String>): Observable<Boolean>
    fun giveLikeReport(map: MutableMap<String, String>): Observable<Boolean>
    fun getShopDetails(map: MutableMap<String, String>): Observable<Shop>
    fun getComplainShop(map: MutableMap<String, String>): Observable<Complain>
    fun collectShop(map: MutableMap<String, String>): Observable<Collect>
    fun getUserDetails(map: MutableMap<String, String>): Observable<UserInfo>
    fun getCrowdorderList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>>
    fun getShareBillList(map: MutableMap<String, String>): Observable<MutableList<ShareBill>>
}
