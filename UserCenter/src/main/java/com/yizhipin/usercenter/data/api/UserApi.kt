package com.yizhipin.usercenter.data.api

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.UserInfo
import io.reactivex.Observable
import retrofit2.http.*


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

    /**
     * 获取购物车数量
     */
    @PUT(Api.CART_COUNT)
    fun getCartCount(@Query("uid") uid: String): Observable<BaseResp<Int>>

    @PUT("${Api.BIND_MOBILE}${"/{"}${BaseConstant.KEY_SP_TOKEN}${"}"}") //绑定手机号
    fun bindMobile(@Path(BaseConstant.KEY_SP_TOKEN) id: String): Observable<BaseResp<Boolean>>

    /**
     * 设置支付密码
     */
    @POST(Api.SET_PAY_PWD)
    fun setPayPwd(): Observable<BaseResp<Boolean>>

    /**
     * 修改支付密码
     */
    @POST(Api.UPDATE_PAY_PWD)
    fun updatePayPwd(): Observable<BaseResp<Boolean>>

    /**
     * 重置支付密码
     */
    @POST(Api.RESET_PAY_PWD)
    fun resetPayPwd(): Observable<BaseResp<Boolean>>
}