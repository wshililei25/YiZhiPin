package com.yizhipin.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.yizhipin.base.R

/**
 * Created by ${XiLei} on 2018/8/19.
 * 主界面底部导航栏
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    init {
        //首页
        val homeItem = BottomNavigationItem(R.drawable.homepage, resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.homepage2)
                .setActiveColorResource(R.color.yMain)
                .setInActiveColorResource(R.color.yBlackDeep)
        //分类
        val categoryItem = BottomNavigationItem(R.drawable.goods, resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.goods2)
                .setActiveColorResource(R.color.yMain)
                .setInActiveColorResource(R.color.yBlackDeep)
        //推广
        val msgItem = BottomNavigationItem(R.drawable.investment, resources.getString(R.string.nav_bar_extend))
                .setInactiveIconResource(R.drawable.investment2)
                .setActiveColorResource(R.color.yMain)
                .setInActiveColorResource(R.color.yBlackDeep)
        //我的
        val userItem = BottomNavigationItem(R.drawable.my, resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.my2)
                .setActiveColorResource(R.color.yMain)
                .setInActiveColorResource(R.color.yBlackDeep)

        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

}