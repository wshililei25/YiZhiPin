package com.yizhipin.base.utils

/**
 * Created by ${XiLei} on 2018/10/13.
 */
object StringUtils {

    /**
     * 手机号中间四位隐藏
     */
    fun setMobileStar(string: String): String {
        return string.replaceRange(3, 7, "****")
    }
}