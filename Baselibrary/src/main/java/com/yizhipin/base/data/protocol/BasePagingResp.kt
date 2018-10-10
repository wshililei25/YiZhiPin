package com.yizhipin.base.data.protocol

import com.yizhipin.base.data.response.Paging

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class BasePagingResp<out T>(val code:String, val msg:String, val data:T,val pi: Paging)