package com.yizhipin.goods.service.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.goods.data.repository.GoodsRepository
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.data.response.ShareBill
import com.yizhipin.goods.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var mRepository: GoodsRepository

    /*
       获取商品详情
    */
    override fun getGoodsDetail(map: MutableMap<String, String>): Observable<Goods> {
        return mRepository.getGoodsDetail(map).convert()
    }

    override fun getEvaluateNew(map: MutableMap<String, String>): Observable<Evaluate> {
        return mRepository.getEvaluateNew(map).convert()
    }

    override fun getReportNew(map: MutableMap<String, String>): Observable<Report> {
        return mRepository.getReportNew(map).convert()
    }

    override fun getCartCountData(map: MutableMap<String, String>): Observable<String> {
        return mRepository.getCartCountData(map).convert()
    }

    override fun getEvaluateList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        return mRepository.getEvaluateList(map).convertPaging()
    }

    override fun getReportList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Evaluate>>> {
        return mRepository.getReportList(map).convertPaging()
    }

    override fun giveLike(map: MutableMap<String, String>): Observable<Boolean> {
        return mRepository.giveLike(map).convertBoolean()
    }

    override fun giveLikeReport(map: MutableMap<String, String>): Observable<Boolean> {
        return mRepository.giveLikeReport(map).convertBoolean()
    }

    override fun getShopDetails(map: MutableMap<String, String>): Observable<Shop> {
        return mRepository.getShopDetails(map).convert()
    }

    override fun getUserDetails(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.getUserDetails(map).convert()
    }
    override fun getCrowdorderList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>> {
        return mRepository.getCrowdorderList(map).convert()
    }

    override fun getComplainShop(map: MutableMap<String, String>): Observable<Complain> {
        return mRepository.getComplainShop(map).convert()
    }

    override fun collectShop(map: MutableMap<String, String>): Observable<Collect> {
        return mRepository.collectShop(map).convert()
    }
    override fun getShareBillList(map: MutableMap<String, String>): Observable<MutableList<ShareBill>> {
        return mRepository.getShareBillList(map).convert()
    }
}