package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.ui.fragment.GoodsDetailTabOneFragment
import com.yizhipin.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class GoodsDetailVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            GoodsDetailTabOneFragment()
        } else {
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}