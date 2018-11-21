package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.data.response.ShareBill
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/*
    商品接口
 */
interface GoodsApi {

    /**
     * 商品列表
     */
    @GET(Api.GOODS_LIST)
    fun getGoodsList(@Query("currentPage") currentPage: String, @Query("primaryCategory") primaryCategory: String
                     , @Query("secondCategory") secondCategory: String, @Query("order") order: String
                     , @Query("orderType") orderType: String): Observable<BasePagingResp<MutableList<Goods>?>>

    /**
     * 商品列表
     */
    @GET(Api.GOODS_LIST)
    fun getSearchGoodsList(@Query("currentPage") currentPage: String, @Query("name") name: String
                           , @Query("order") order: String, @Query("orderType") orderType: String): Observable<BasePagingResp<MutableList<Goods>?>>

    /**
     * 店铺的商品列表
     */
    @GET(Api.GOODS_LIST)
    fun getShopGoodsList(@Query("currentPage") currentPage: String, @Query("shopId") shopId: String): Observable<BasePagingResp<MutableList<Goods>?>>

    /**
     * 收藏的商品列表
     */
    @GET(Api.COLLECT_GOODS)
    fun getCollectGoodsList(@Query("currentPage") currentPage: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<CollectGoods>?>>

    /**
     * 收藏的店铺列表
     */
    @GET(Api.COLLECT_SHOP_LIST)
    fun getCollectShopList(@Query("currentPage") currentPage: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<CollectShop>?>>

    /*
        获取商品详情
     */
    @GET("${Api.GOODS_DETAIL}${"/{id}"}")
    fun getGoodsDetail(@Path("id") id: String, @Query("loginUid") loginUid: String): Observable<BaseResp<Goods>>

    /**
     * 最新评价
     */
    @GET("${Api.EVALUATE_NEW}")
    fun getEvaluateNew(@Query("pid") pid: String): Observable<BaseResp<Evaluate>>

    /**
     * 最新体验报告
     */
    @GET("${Api.REPORT_NEW}")
    fun getReportNew(@Query("pid") pid: String): Observable<BaseResp<Report>>

    /**
     * 购物车数量
     */
    @GET("${Api.CART_COUNT}")
    fun getCartCountData(@Query("uid") uid: String): Observable<BaseResp<String>>

    /**
     * 评价列表(未登陆时)
     */
    @GET("${Api.EVALUATE_LIST}")
    fun getEvaluateListNotLogin(@Query("currentPage") currentPage: String, @Query("pid") pid: String, @Query("shopId") shopId: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 评价列表
     */
    @GET("${Api.EVALUATE_LIST}")
    fun getEvaluateList(@Query("currentPage") currentPage: String, @Query("pid") pid: String, @Query("shopId") shopId: String, @Query("loginUid") loginUid: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 体验报告列表(未登陆时)
     */
    @GET("${Api.REPORT_LIST}")
    fun getReportListNotLogin(@Query("currentPage") currentPage: String, @Query("pid") pid: String, @Query("shopId") shopId: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 体验报告列表
     */
    @GET("${Api.REPORT_LIST}")
    fun getReportList(@Query("currentPage") currentPage: String, @Query("pid") pid: String, @Query("shopId") shopId: String, @Query("uid") uid: String, @Query("loginUid") loginUid: String): Observable<BasePagingResp<MutableList<Evaluate>>>

    /**
     * 点赞评价
     */
    @POST("${Api.GIVE_LIKE}")
    fun giveLike(): Observable<BaseResp<Boolean>>

    /**
     * 点赞体验报告
     */
    @POST("${Api.GIVE_LIKE_REPORT}")
    fun giveLikeReport(): Observable<BaseResp<Boolean>>

    /**
     * 店铺详情
     */
    @GET("${Api.SHOP_DETAIL}${"/{id}"}")
    fun getShopDetails(@Path("id") id: String, @Query("loginUid") loginUid: String): Observable<BaseResp<Shop>>

    /**
     * 用户详情
     */
    @GET("${Api.EDIT_USER_INFO}${"/{id}"}")
    fun getUserDetails(@Path("id") id: String): Observable<BaseResp<UserInfo>>

    /**
     * 拼单列表
     */
    @GET(Api.CROWDORDER_LIST)
    fun getCrowdorderList(@Query("uid") uid: String): Observable<BaseResp<MutableList<UserInfo>>>

    /**
     * 举报投诉
     */
    @POST(Api.COMPLAIN_SHOP)
    fun getComplainShop(): Observable<BaseResp<Complain>>

    /**
     * 收藏店铺
     */
    @POST(Api.COLLECT_SHOP)
    fun collectShop(): Observable<BaseResp<Collect>>

    /**
     * 附近品团列表
     */
    @GET(Api.SHARE_BILL_LIST)
    fun getShareBillList(@Query("lng") lng: String, @Query("lat") lat: String, @Query("pid") pid: String): Observable<BaseResp<MutableList<ShareBill>>>
}
