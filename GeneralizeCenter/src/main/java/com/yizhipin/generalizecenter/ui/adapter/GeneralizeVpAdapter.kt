package com.yizhipin.generalizecenter.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.generalizecenter.ui.fragment.GeneralizeGoodsFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class GeneralizeVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mList = arrayOf("正在竞价", "下期预告", "推广中", "订阅关注")

    override fun getItem(position: Int): Fragment {
        val fragment = GeneralizeGoodsFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }

}