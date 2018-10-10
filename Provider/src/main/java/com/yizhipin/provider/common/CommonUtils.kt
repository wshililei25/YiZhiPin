package com.yizhipin.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.router.RouterPath

/**
 * Created by ${XiLei} on 2018/8/21.
 */

/**
 * 判断是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

/*
    如果已经登录，进行传入的方法处理
    如果没有登录，进入登录界面
 */
fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}