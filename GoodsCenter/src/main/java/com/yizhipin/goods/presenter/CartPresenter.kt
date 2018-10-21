package com.yizhipin.goods.presenter

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.presenter.view.CartView
import com.yizhipin.goods.service.impl.CartServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CartPresenter @Inject constructor() : BasePresenter<CartView>() {

    @Inject
    lateinit var mCartServiceImpl: CartServiceImpl

    /**
     * 购物车列表
     */
    fun getCartList(map: MutableMap<String, String>) {

//        mView.showLoading()
        mCartServiceImpl.getCartList(map)
                .execute(object : BaseSubscriber<MutableList<Cart>?>(mView) {
                    override fun onNext(t: MutableList<Cart>?) {
                        mView.onGetCartListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    /**
     * 删除购物车商品
     */
    fun deleteCartList(map: MutableMap<String, String>) {
        mView.showLoading()
        mCartServiceImpl.deleteCartList(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 更新购物车商品数量
     */
    fun updateCartGoodsCount(map: MutableMap<String, String>) {
        mView.showLoading()
        mCartServiceImpl.updateCartGoodsCount(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onUpdateCartGoodsCountSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /*
    提交购物车商品
 */
    fun submitCart(list: MutableList<Goods>, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mCartServiceImpl.submitCart(list, totalPrice).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        }, mLifecycleProvider)

    }
}