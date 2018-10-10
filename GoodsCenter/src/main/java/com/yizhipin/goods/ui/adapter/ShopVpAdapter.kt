package com.yizhipin.goods.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.ui.fragment.CategorySecondFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class ShopVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf<String>("商品", "评价", "体验报告", "详情")

    override fun getItem(position: Int): Fragment {
        val fragment = CategorySecondFragment()
     /*   val bundle = Bundle()
        bundle.putParcelable(GoodsConstant.KEY_CATEGORY_ITEM, mList[position])
        fragment.arguments = bundle*/
        return fragment
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }
}