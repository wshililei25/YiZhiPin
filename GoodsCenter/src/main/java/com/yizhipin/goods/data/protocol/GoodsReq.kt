package com.yizhipin.goods.data.protocol

/**
 * Created by ${XiLei} on 2018/8/23.
 */
interface GoodsReq {


    class GetGoodsListReq(val categoryId: Int, val pageNo: Int)

    class GetGoodsListByKeywordReq(val keyword: String, val pageNo: Int)

}