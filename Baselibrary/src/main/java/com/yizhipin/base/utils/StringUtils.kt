package com.yizhipin.base.utils

/**
 * Created by ${XiLei} on 2018/10/13.
 */
object StringUtils {

    fun setMobileStar(string: String):String{
        return string.replaceRange(3, 7, "****")
    }
}