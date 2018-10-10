package com.yizhipin.usercenter.data.repository

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.net.RetrofitFactoryPut
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.data.api.UserApi
import com.yizhipin.usercenter.data.response.UserInfo
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class UserRepository @Inject constructor() {

    /**
     * 登录
     */
    fun login(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {

        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .login()
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .getUserInfo(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
    }

    /**
     * 编辑用户信息
     */
    fun editUserInfo(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryPut(map).create(UserApi::class.java)
                .editUserInfo(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
    }

    /**
     * 绑定手机号
     */
    fun bindMobile(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPut(map).create(UserApi::class.java)
                .bindMobile(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
    }
}