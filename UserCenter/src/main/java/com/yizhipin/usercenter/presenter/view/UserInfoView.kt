package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.presenter.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserInfoView : BaseView {
    fun getUserResult(result: UserInfo)
    fun onEditUserResult(result: UserInfo)
    fun onGetCartSuccess(result: Int)
}