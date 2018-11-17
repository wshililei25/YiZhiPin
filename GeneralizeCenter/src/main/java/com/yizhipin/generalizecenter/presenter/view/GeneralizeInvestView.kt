package com.yizhipin.generalizecenter.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.data.response.InvestDetails
import com.yizhipin.generalizecenter.data.response.InvestList

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GeneralizeInvestView : BaseView {
    fun onGetInvestAmountSuccess(result: GeneralizeInvestAmount)
    fun onGetInvestListSuccess(result: MutableList<GeneralizeInvest>)
    fun onGetInvestDetailsListSuccess(result: MutableList<InvestList>)
    fun onGetInvestDetailsSuccess(result: InvestDetails)
}