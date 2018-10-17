package com.yizhipin.goods.service

import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.data.response.CartGoods
import io.reactivex.Observable

/*
    购物车 业务 接口
 */
interface CartService {
    /*
        添加商品到购物车
     */
    fun addCart(map: MutableMap<String, String>): Observable<CartGoods>

    fun collectGood(map: MutableMap<String, String>): Observable<GoodsCollect>

    /*
        获取购物车列表
     */
    fun getCartList(map: MutableMap<String, String>): Observable<MutableList<Cart>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(map: MutableMap<String, String>): Observable<Boolean>

    /*
        购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}
