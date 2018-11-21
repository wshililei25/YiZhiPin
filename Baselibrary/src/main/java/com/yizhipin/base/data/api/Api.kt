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
        const val COLLECT_SHOP = "api/ShopCollection"//收藏店铺
        const val COMPLAIN_SHOP = "api/ShopComplaint"//举报投诉
        const val DEFAULT_ADDRESS = "api/UserAddress/Default"//获取默认地址
        const val SUBMIT_ORDER = "api/Order"//提交订单
        const val SUBMIT_ORDER_RESIDE = "api/Order/Homestay"//提交订单(一品小住)
        const val ADD_CART = "api/ShopCart"//加入购物车
        const val CART_LIST = "api/ShopCart/List"//购物车列表
        const val COLLECT_GOOD = "api/ProductCollection"//收藏商品
        const val DELETE_CART_GOODS = "api/ShopCart"//删除购物车商品
        const val CART_COUNT = "api/ShopCart/Count"//获取购物车数量
        const val HOT_GOODS_LIST = "api/Product/List"//热门商品列表
        const val SET_PAY_PWD = "api/WebUser/PayPassword/Set"//设置支付密码
        const val UPDATE_PAY_PWD = "api/WebUser/PayPassword/Change"//修改支付密码
        const val RESET_PAY_PWD = "api/WebUser/PayPassword/Reset"//重置支付密码
        const val COUPON_LIST = "api/UserConpon/Page"//优惠券列表
        const val ORDER_LIST = "api/Order/Page"//订单列表
        const val ORDER_CANCEL = "api/Order"//取消订单
        const val COLLECT_GOODS = "api/ProductCollection/Page"//收藏的商品列表
        const val COLLECT_SHOP_LIST = "api/ShopCollection/Page"//收藏的店铺列表
        const val GENERALIZE_LIST = "api/Investment/Page"//推广中的商品列表
        const val GENERALIZE_DETAILS = "api/Investment"//推广中的商品详情
        const val GENERALIZE_GROUP_DETAILS = "api/Investment/Group/Detail"//投资团详情
        const val PAY_PERSONAGE = "api/Investment/User"//个人出价投资
        const val GENERALIZE_INVEST_AMOUNT = "api/InvestmentIncome/Total"//投资收益金额
        const val GENERALIZE_INVEST_LIST = "api/InvestmentIncome/Record"//投资推广列表
        const val INVEST_DETAILS_LIST = "api/InvestmentIncome/List"//投资明细列表
        const val INVEST_DETAILS = "api/InvestmentIncome/My/Detail"//投资详情
        const val SEARCH_KEYWORD = "api/Product/SearchKeyWords"//关键字
        const val END_TIME = "api/Investment/EndTime"//竞价结束时间
        const val CROWDORDER_LIST = "api/Tuan/List"//拼单列表
        const val SHARE_BILL_LIST = "api/Tuan/NearBy"//附近品团列表
    }
}