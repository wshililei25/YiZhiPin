package com.yizhipin.presenter.view

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.data.response.Banner

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface HomeView : BaseView {
    fun onGetBannerSuccess(result: MutableList<Banner>)
    fun onGetGoodsListSuccess(result: MutableList<Goods>)
}