package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.RetrofitFactory
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.goods.data.api.CartApi
import com.yizhipin.goods.data.protocol.AddCartReq
import com.yizhipin.goods.data.response.CartGoods
import com.yizhipin.goods.data.protocol.DeleteCartReq
import com.yizhipin.goods.data.protocol.SubmitCartReq
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车数据层
 */
class CartRepository @Inject constructor() {

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>> {
        return RetrofitFactory.instance.create(CartApi::class.java).getCartList()
    }

    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResp<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(CartApi::class.java).deleteCartList(DeleteCartReq(list))
    }

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<BaseResp<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java).submitCart(SubmitCartReq(list, totalPrice))
    }


}
