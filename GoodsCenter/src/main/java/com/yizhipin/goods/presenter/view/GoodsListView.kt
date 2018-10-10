package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Goods

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GoodsListView : BaseView {
    fun onGetGoodsListSuccess(result: MutableList<Goods>?)
}