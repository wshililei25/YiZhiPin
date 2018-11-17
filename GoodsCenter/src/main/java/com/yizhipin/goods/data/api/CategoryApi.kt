package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.data.response.SearchKeyword
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/*
    商品分类接口
 */
interface CategoryApi {

    /**
     * 一级分类
     */
    @GET(Api.CATEGORY)
    fun getCategory(): Observable<BaseResp<MutableList<Category>?>>
    /**
     * 二级分类
     */
    @GET(Api.CATEGORY_SECOND)
    fun getCategorySecond(@Query("primaryCategory") primaryCategory: String): Observable<BaseResp<MutableList<CategorySecond>?>>

    /**
     * 关键字
     */
    @GET(Api.SEARCH_KEYWORD)
    fun getSearchKeyword(): Observable<BaseResp<MutableList<SearchKeyword>?>>

}
