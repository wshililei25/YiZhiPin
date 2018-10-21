package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.*
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.goods.data.api.CartApi
import com.yizhipin.goods.data.protocol.SubmitCartReq
import com.yizhipin.goods.data.response.Cart
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车数据层
 */
class CartRepository @Inject constructor() {

    /*
        获取购物车列表
     */
    fun getCartList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<Cart>?>> {
        return RetrofitFactoryGet().create(CartApi::class.java).getCartList(map["uid"]!!)
    }

    /*
        添加商品到购物车
     */
    fun addCart(map: MutableMap<String, String>): Observable<BaseResp<Goods>> {
        return RetrofitFactoryPost(map).create(CartApi::class.java).addCart()
    }

    fun collectGood(map: MutableMap<String, String>): Observable<BaseResp<GoodsCollect>> {
        return RetrofitFactoryPost(map).create(CartApi::class.java).collectGood()
    }

    /*
        删除购物车商品
     */
    fun deleteCartList(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryDelete(map).create(CartApi::class.java).deleteCartList(map["id"]!!)
    }

    fun updateCartGoodsCount(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryPut(map).create(CartApi::class.java).updateCartGoodsCount(map["id"]!!,map["count"]!!)
    }

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<Goods>, totalPrice: Long): Observable<BaseResp<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java).submitCart(SubmitCartReq(list, totalPrice))
    }


}
