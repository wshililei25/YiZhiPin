package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Cart
import com.yizhipin.goods.data.response.CartGoods

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CartView : BaseView {
    fun onGetCartListSuccess(result: MutableList<Cart>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}