package com.yizhipin.goods.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.Shop
import com.yizhipin.goods.data.response.*
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /*
      根据关键字查询商品
   */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /*
      获取商品详情
   */
    fun getGoodsDetail(map: MutableMap<String, String>): Observable<Goods>

    /**
     * 最新评价
     */
    fun getEvaluateNew(map: MutableMap<String, String>): Observable<Evaluate>

    fun getReportNew(map: MutableMap<String, String>): Observable<Report>

    fun getEvaluateList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>>
    fun getReportList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>>
    fun giveLike(map: MutableMap<String, String>): Observable<Boolean>
    fun giveLikeReport(map: MutableMap<String, String>): Observable<Boolean>
    fun getShopDetails(map: MutableMap<String, String>): Observable<Shop>
    fun getComplainShop(map: MutableMap<String, String>): Observable<Complain>
}
