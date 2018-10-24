package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.CollectGoods
import com.yizhipin.base.data.response.CollectShop
import com.yizhipin.base.presenter.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface CollectView : BaseView {
    fun onGetCollectGoodsListSuccess(result: BasePagingResp<MutableList<CollectGoods>?>)
    fun onGetCollectShopListSuccess(result: BasePagingResp<MutableList<CollectShop>?>)
}