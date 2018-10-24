package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.ui.fragment.CollectGoodsFragment
import com.yizhipin.goods.ui.fragment.CollectShopFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class CollectVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("商品", "店铺", "浏览记录")

    override fun getItem(position: Int): Fragment {
//        val fragment = CollectGoodsFragment()
        val bunder = Bundle()
        when (position) {
            0 -> return CollectGoodsFragment()
            1 -> return CollectShopFragment()
            2 -> return CollectGoodsFragment()
        }
//        fragment.arguments = bunder
        return null!!
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}