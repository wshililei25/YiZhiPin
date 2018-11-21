package com.yizhipin.goods.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.goods.data.response.ShareBill

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface ShareBillView : BaseView {
    fun onGetShareBillListSuccess(result: MutableList<ShareBill>)
}