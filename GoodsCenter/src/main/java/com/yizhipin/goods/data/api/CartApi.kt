package com.yizhipin.goods.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.goods.data.protocol.DeleteCartReq
import com.yizhipin.goods.data.protocol.SubmitCartReq
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.data.response.CartGoods
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
    fun addCart(): Observable<BaseResp<CartGoods>>

    /**
     * 收藏商品
     */
    @POST(Api.COLLECT_GOOD)
    fun collectGood(): Observable<BaseResp<GoodsCollect>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
