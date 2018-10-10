package com.yizhipin.goods.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.CartGoods
import com.yizhipin.goods.presenter.view.CartView
import com.yizhipin.goods.service.impl.CartServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CartPresenter @Inject constructor() : BasePresenter<CartView>() {

    @Inject
    lateinit var mCartServiceImpl: CartServiceImpl

    fun getCartList() {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mCartServiceImpl.getCartList()
                .execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        mView.onGetCartListResult(t)
                    }
                }, mLifecycleProvider)

    }

    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mCartServiceImpl.deleteCartList(list).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
    提交购物车商品
 */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mCartServiceImpl.submitCart(list,totalPrice).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        }, mLifecycleProvider)

    }
}