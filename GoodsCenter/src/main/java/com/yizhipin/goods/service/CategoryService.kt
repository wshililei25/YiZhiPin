package com.yizhipin.goods.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.CollectGoods
import com.yizhipin.base.data.response.CollectShop
import com.yizhipin.base.data.response.Goods
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.data.response.SearchKeyword
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CategoryService {

    fun getCategoryAll(): Observable<MutableList<Category>?>
    fun getSearchKeyword(): Observable<MutableList<SearchKeyword>?>
    fun getCategorySecond(map: MutableMap<String, String>): Observable<MutableList<CategorySecond>?>
    fun getGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>>
    fun getSearchGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>>
    fun getShopGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>>
    fun getCollectGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<CollectGoods>?>>
    fun getCollectShopList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<CollectShop>?>>
}
