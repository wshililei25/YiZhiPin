package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.ui.fragment.OrderFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class OrderVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("全部", "待付款", "待收货", "已完成", "已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bunder = Bundle()
        bunder.putInt(OrderConstant.KEY_ORDER_STATUS, position)
        fragment.arguments = bunder
        return fragment
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}