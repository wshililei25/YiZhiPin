package com.yizhipin.goods.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.fragment.EvaluateFragment
import kotlinx.android.synthetic.main.activity_evaluate.*

/**
 * Created by ${XiLei} on 2018/9/2.
 * 评价列表
 */
class EvaluateActivity : BaseActivity() {

    @Autowired(name = GoodsConstant.KEY_EVA_COUNT) //注解接收上个页面的传参
    @JvmField
    var mEvaCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate)
        initView()
    }

    private fun initView() {
        mHeaderBar.getTiTleTv().text = "所有评价(${mEvaCount})"
        supportFragmentManager.beginTransaction().add(R.id.mFrameLayout, EvaluateFragment()).commit()
    }

}