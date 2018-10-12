package com.yizhipin.goods.data.protocol

/**
 * Created by ${XiLei} on 2018/8/23.
 */
interface GoodsReq {

    class GetGoodsListByKeywordReq(val keyword: String, val pageNo: Int)

}