package com.yizhipin.goods.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.fragment.CrowdorderFragment
import com.yizhipin.goods.ui.fragment.EvaluateFragment
import com.yizhipin.goods.ui.fragment.ReportFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class UserVpAdapter(fragmentManager: FragmentManager, val uid: String) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf("拼单", "体验报告", "评价")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        bundle.putString(GoodsConstant.KEY_USER_ID, uid)

        if (position == 0) {
            val fragment = CrowdorderFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 1) {
            val fragment = ReportFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 2) {
            val fragment = EvaluateFragment()
            fragment.arguments = bundle
            return fragment
        }
        return null!!
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }


}