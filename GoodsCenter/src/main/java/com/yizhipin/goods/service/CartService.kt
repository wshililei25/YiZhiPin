package com.yizhipin.goods.service

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

    /*
        获取购物车列表
     */
    fun getCartList(map: MutableMap<String, String>): Observable<MutableList<Cart>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /*
        购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}
