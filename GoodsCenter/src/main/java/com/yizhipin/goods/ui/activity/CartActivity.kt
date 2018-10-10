package com.yizhipin.goods.ui.activity

import android.os.Bundle
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.ui.fragment.CartFragment

/**
 * Created by ${XiLei} on 2018/9/24.
 */
class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.mFragment)
        (fragment as CartFragment).setBackVisible(true)
    }
}