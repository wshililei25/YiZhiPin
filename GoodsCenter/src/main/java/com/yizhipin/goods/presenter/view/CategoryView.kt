package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CategoryView : BaseView {
    fun onGetCategoryAllSuccess(result: MutableList<Category>?)
    fun onGetCategorySencondSuccess(result: MutableList<CategorySecond>?)
    fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<Goods>?>)
}