package com.yizhipin.usercenter.utils

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant

/**
 *本地存储用户相关信息
 */
object UserPrefsUtils {

    /**
     *  退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.id ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.mobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.imgurl ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NICKNAME, userInfo?.nickname ?: "")
        AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, userInfo?.push ?: false)
        AppPrefsUtils.putString(ProviderConstant.KEY_AMOUNT, userInfo?.amount ?: "0")
        AppPrefsUtils.putString(ProviderConstant.KEY_PAY_PWD, userInfo?.payPwd ?: "")
    }
}
