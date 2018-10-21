package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Cart

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CartView : BaseView {
    fun onGetCartListSuccess(result: MutableList<Cart>?)
    fun onDeleteCartListSuccess(result: Boolean)
    fun onUpdateCartGoodsCountSuccess(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}