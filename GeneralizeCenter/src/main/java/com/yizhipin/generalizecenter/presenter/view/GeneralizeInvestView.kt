package com.yizhipin.generalizecenter.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GeneralizeInvestView : BaseView {
    fun onGetInvestListSuccess(result: GeneralizeInvestAmount)
}