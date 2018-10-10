package com.yizhipin.goods.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.ui.fragment.CategorySecondFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class CategoryVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mList: MutableList<Category> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        val fragment = CategorySecondFragment()
        val bundle = Bundle()
        bundle.putParcelable(GoodsConstant.KEY_CATEGORY_ITEM, mList[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position].cnName
    }

    fun setData(list: MutableList<Category>) {
        mList = list
        notifyDataSetChanged()
    }
}