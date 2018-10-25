package com.yizhipin.generalizecenter.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeVpAdapter
import kotlinx.android.synthetic.main.fragment_generalize.*

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class GeneralizeFragment : BaseFragment() {

    private lateinit var mVpAdapter: GeneralizeVpAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_generalize, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mVpAdapter = GeneralizeVpAdapter(childFragmentManager)
        mVp.adapter = mVpAdapter
        mTab.setupWithViewPager(mVp)
    }

}



