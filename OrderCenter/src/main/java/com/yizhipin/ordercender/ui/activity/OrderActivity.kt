package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class OrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_SCROLLABLE
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, 0)
    }
}