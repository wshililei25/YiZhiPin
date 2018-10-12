package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.goods.data.api.CategoryApi
import com.yizhipin.goods.data.api.GoodsApi
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class CategoryRepository @Inject constructor() {

    fun getCategoryAll(): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactoryGet().create(CategoryApi::class.java)
                .getCategory()
    }

    fun getCategorySecond(map: MutableMap<String, String>): Observable<BaseResp<MutableList<CategorySecond>?>> {
        return RetrofitFactoryGet().create(CategoryApi::class.java)
                .getCategorySecond(map["primaryCategory"]!!)
    }

    fun getGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java)
                .getGoodsList(map["currentPage"]!!, map["primaryCategory"]!!, map["secondCategory"]!!, map["order"]!!
                        , map["orderType"]!!)
    }

    fun getShopGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>> {
        return RetrofitFactoryGet().create(GoodsApi::class.java)
                .getShopGoodsList(map["currentPage"]!!, map["shopId"]!!)
    }


}