package com.yizhipin.usercenter.data.api

/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface Api {
    companion object {

        const val LOGIN = "api/WebUser/Login/Sms" //登录
        const val EDIT_USER_INFO = "api/WebUser"//编辑用户信息
        const val BIND_MOBILE = "api/WebUser/BindingMobile"//编辑用户信息
        const val ADDRESS_LIST = "api/UserAddress/List"//收货地址列表
        const val ADD_ADDRESS = "api/UserAddress"//新增收货地址 \  修改收货地址 \  删除收货地址
        const val SET_DEFAULT_ADDRESS = "api/UserAddress/Default"//设置默认收货地址
        const val BANNER = "api/Banner/List"//获取banner
        const val CATEGORY = "api/Product/Category"//一级分类
        const val CATEGORY_SECOND = "api/Product/Category/List"//二级分类
        const val GOODS_LIST = "api/Product/Page"//商品列表
        const val GOODS_DETAIL = "api/Product"//商品详情
        const val EVALUATE_NEW = "api/ProductEva/New"//最新评价
        const val REPORT_NEW = "api/Experience/Product"//最新体验报告
        const val EVALUATE_LIST = "api/ProductEva/Page"//评价列表
        const val REPORT_LIST = "api/Experience/Page"//体验报告列表
        const val GIVE_LIKE = "api/ProductEva/Zan"//点赞评价
        const val GIVE_LIKE_REPORT = "api/Experience/Zan"//点赞体验报告
        const val SHOP_DETAIL = "api/Shop"//店铺详情
    }
}