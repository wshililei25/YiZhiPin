package com.yizhipin.goods.common

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class GoodsConstant {
    companion object {
        //商品分类ID
        const val KEY_CATEGARY_ID = "categoryId"
        //搜索历史 本地存储
        const val SP_SEARCH_HISTORY = "search_history"
        //搜索商品类型
        const val KEY_SEARCH_GOODS_TYPE = "search_goods_type"
        //按关键字搜索
        const  val SEARCH_GOODS_TYPE_KEYWORD = 1
        //搜索关键字
        const val KEY_GOODS_KEYWORD = "goods_keyword"
        //sku分隔标识
        const val SKU_SEPARATOR = ","
        //商品ID
        const  val KEY_GOODS_ID = "goods_id"
        //一级分类item
        const  val KEY_CATEGORY_ITEM = "category_item"
        //评价总数量
        const  val KEY_EVA_COUNT = "eva_count"
        //店铺
        const  val KEY_SHOP = "shop"
        //店铺ID
        const  val KEY_SHOP_ID = "shop_id"
        //用户ID
        const  val KEY_USER_ID = "userId"
        //商品item
        const  val KEY_GOOD_ITEM = "good_item"
    }
}