package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Shop

/*
    店铺详情 视图回调
 */
interface ShopView : BaseView {

    fun onGetShopDetailsSuccess(result: Shop)

}
