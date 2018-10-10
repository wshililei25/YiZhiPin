package com.yizhipin.usercenter.service.impl

import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.usercenter.data.repository.UserRepository
import com.yizhipin.usercenter.data.response.UserInfo
import com.yizhipin.usercenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var mRepository: UserRepository

    /**
     * 登录
     */
    override fun login(map: MutableMap<String, String>): Observable<UserInfo> {

        return mRepository.login(map)
                .convert()
    }

    /**
     * 获取用户信息
     */
    override fun getUserInfo(): Observable<UserInfo> {
        return mRepository.getUserInfo()
                .convert()
    }

    /**
     * 编辑用户资料
     */
    override fun editUserInfo(map : MutableMap<String,String>): Observable<UserInfo> {
        return mRepository.editUserInfo(map)
                .convert()
    }

    /**
     * 绑定手机号
     */
    override fun bindMobile(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.bindMobile(map)
                .convertBoolean()
    }
}