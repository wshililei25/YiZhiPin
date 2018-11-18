package com.yizhipin.usercenter.service

import com.yizhipin.base.data.response.UserInfo
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserService {

    fun login(map: MutableMap<String, String>): Observable<UserInfo>
    fun getUserInfo(): Observable<UserInfo>
    fun editUserInfo(map: MutableMap<String, String>): Observable<UserInfo>
    fun bindMobile(map: MutableMap<String, String>): Observable<Boolean>
    fun getCartCount(map: MutableMap<String, String>): Observable<Int>
    fun setPayPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun updatePayPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun resetPayPwd(map: MutableMap<String, String>): Observable<Boolean>
}
