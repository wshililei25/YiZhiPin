package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.ui.adapter.CollectVpAdapter
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_collect.*

/**
 * Created by ${XiLei} on 2018/9/25.
 */
@Route(path = RouterPath.GoodsCenter.PATH_GOODS_COLLECT)
class CollectActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = CollectVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

//        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, 0)
    }
}