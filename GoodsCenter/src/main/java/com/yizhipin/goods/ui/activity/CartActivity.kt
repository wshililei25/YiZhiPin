package com.yizhipin.goods.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.ui.fragment.CartFragment
import com.yizhipin.provider.router.RouterPath

/**
 * Created by ${XiLei} on 2018/9/24.
 */

@Route(path = RouterPath.GoodsCenter.PATH_GOODS_CART)
class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.mFragment)
        (fragment as CartFragment).setBackVisible(true)
    }
}