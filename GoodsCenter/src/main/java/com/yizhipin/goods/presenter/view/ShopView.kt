package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.Complain

/*
    店铺详情 视图回调
 */
interface ShopView : BaseView {

    fun onGetShopDetailsSuccess(result: Shop)
    fun onComplainShopSuccess(result: Complain)
    fun oncollectShopSuccess(result: Collect)

}
