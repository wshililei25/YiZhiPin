package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.CartGoods

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CartView : BaseView {
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}