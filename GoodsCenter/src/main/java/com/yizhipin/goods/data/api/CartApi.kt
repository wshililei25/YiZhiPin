package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.goods.data.protocol.SubmitCartReq
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.*

/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @GET(Api.CART_LIST)
    fun getCartList(@Query("uid") uid: String): Observable<BaseResp<MutableList<Cart>?>>

    /*
        添加商品到购物车
     */
    @POST(Api.ADD_CART)
    fun addCart(): Observable<BaseResp<Goods>>

    /**
     * 收藏商品
     */
    @POST(Api.COLLECT_GOOD)
    fun collectGood(): Observable<BaseResp<GoodsCollect>>

    /**
     *  删除购物车商品
     */
    @DELETE("${Api.DELETE_CART_GOODS}${"/{id}"}")
    fun deleteCartList(@Path("id") id: String): Observable<BaseResp<String>>

    /**
     *  更新购物车商品数量
     */
    @PUT("${Api.DELETE_CART_GOODS}${"/{id}"}")
    fun updateCartGoodsCount(@Path("id") id: String, @Query("count") count: String): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
