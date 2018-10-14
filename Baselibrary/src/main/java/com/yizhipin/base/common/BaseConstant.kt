package com.yizhipin.base.common

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class BaseConstant {
    companion object {
        //图片服务器地址
        const val IMAGE_SERVICE_ADDRESS = "http://nian.im/storage/"
        //接口地址
        const val SERVICE_ADDRESS = "http://nian.im/localbuy/"
        //SP表名
        const val TABLE_PREFS = "YiZhiPin"
        //Token Key
        const val KEY_SP_TOKEN = "id"


        //商品集合
        const val KEY_GOODS_LIST = "goodsList"
        //是否拼单  还是原价购买
        const val KEY_IS_PIN = "isPin"
    }
}