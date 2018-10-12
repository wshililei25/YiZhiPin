package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.presenter.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GoodsListView : BaseView {
    fun onGetGoodsListSuccess(result: MutableList<Goods>?)
}