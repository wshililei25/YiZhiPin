package com.yizhipin.goods.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.base.data.response.Shop
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.fragment.EvaluateFragment
import com.yizhipin.goods.ui.fragment.GoodsFragment
import com.yizhipin.goods.ui.fragment.ReportFragment
import com.yizhipin.goods.ui.fragment.ShopDetailsFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class ShopVpAdapter(fragmentManager: FragmentManager, val mShopId: String) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf("商品", "评价", "体验报告", "详情")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        bundle.putString(GoodsConstant.KEY_SHOP_ID, mShopId)

        if (position == 0) {
            val fragment = GoodsFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 1) {
            val fragment = EvaluateFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 2) {
            val fragment = ReportFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 3) {
            val fragment = ShopDetailsFragment()
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