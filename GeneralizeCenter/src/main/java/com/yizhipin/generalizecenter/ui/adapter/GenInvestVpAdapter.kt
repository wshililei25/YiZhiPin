package com.yizhipin.generalizecenter.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.ui.fragment.GeneralizeInvestFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class GenInvestVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("全部", "收益中", "竞价中", "已结束", "竞价失败")

    override fun getItem(position: Int): Fragment {
        val fragment = GeneralizeInvestFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(GeneralizeConstant.KEY_INVEST_STATUS, "")
            1 -> bunder.putString(GeneralizeConstant.KEY_INVEST_STATUS, "1")
            2 -> bunder.putString(GeneralizeConstant.KEY_INVEST_STATUS, "2")
            3 -> bunder.putString(GeneralizeConstant.KEY_INVEST_STATUS, "3")
            4 -> bunder.putString(GeneralizeConstant.KEY_INVEST_STATUS, "4")
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