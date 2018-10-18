package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.usercenter.data.response.UserInfo

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserInfoView : BaseView {
    fun getUserResult(result:UserInfo)
    fun onEditUserResult(result:UserInfo)
    fun onGetCartSuccess(result:Int)
}