package com.yizhipin.usercenter.data.api

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.usercenter.data.response.UserInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface UserApi {

    @POST(Api.LOGIN) //登录
    fun login(): Observable<BaseResp<UserInfo>>

    @GET("${Api.EDIT_USER_INFO}${"/{"}${BaseConstant.KEY_SP_TOKEN}${"}"}") //获取用户信息
    fun getUserInfo(@Path(BaseConstant.KEY_SP_TOKEN) id: String): Observable<BaseResp<UserInfo>>

    //    @PUT(Api.EDIT_USER_INFO.plus("/{id}")) //编辑用户信息
    @PUT("${Api.EDIT_USER_INFO}${"/{"}${BaseConstant.KEY_SP_TOKEN}${"}"}") //编辑用户信息
    fun editUserInfo(@Path(BaseConstant.KEY_SP_TOKEN) id: String): Observable<BaseResp<UserInfo>>

    @PUT("${Api.BIND_MOBILE}${"/{"}${BaseConstant.KEY_SP_TOKEN}${"}"}") //绑定手机号
    fun bindMobile(@Path(BaseConstant.KEY_SP_TOKEN) id: String): Observable<BaseResp<Boolean>>
}