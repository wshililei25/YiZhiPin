package com.yizhipin.goods.service.impl

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.goods.data.repository.CartRepository
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.service.CartService
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车 业务层实现类
 */
class CartServiceImpl @Inject constructor() : CartService {
    @Inject
    lateinit var repository: CartRepository

    /*
        加入购物车
     */
    override fun addCart(map: MutableMap<String, String>): Observable<Goods> {
        return repository.addCart(map).convert()
    }

    override fun collectGood(map: MutableMap<String, String>): Observable<GoodsCollect> {
        return repository.collectGood(map).convert()
    }

    /*
        获取购物车列表
     */
    override fun getCartList(map: MutableMap<String, String>): Observable<MutableList<Cart>?> {
        return repository.getCartList(map).convert()
    }

    /*
        删除购物车商品
     */
    override fun deleteCartList(map: MutableMap<String, String>): Observable<Boolean> {
        return repository.deleteCartList(map).convertBoolean()
    }

    override fun updateCartGoodsCount(map: MutableMap<String, String>): Observable<Boolean> {
        return repository.updateCartGoodsCount(map).convertBoolean()
    }

    /*
        提交购物车商品
     */
    override fun submitCart(list: MutableList<Goods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(list, totalPrice).convert()
    }
}
