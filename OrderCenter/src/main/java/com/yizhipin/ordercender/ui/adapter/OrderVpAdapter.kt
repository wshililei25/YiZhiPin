package com.yizhipin.ordercender.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.common.OrderStatus
import com.yizhipin.ordercender.ui.fragment.OrderFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class OrderVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("全部", "待付款", "拼单中", "待发货", "待收货", "待评价", "售后")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
            1 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_PAY)
            2 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_PIN)
            3 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_SEND)
            4 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_CONFIRM)
            5 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_EVALUATE)
            6 -> bunder.putString(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_AFTER_SALE)
        }
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