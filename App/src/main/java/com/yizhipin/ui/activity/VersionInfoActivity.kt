package com.yizhipin.ui.activity

import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_version_info.*

/**
 * Created by ${XiLei} on 2018/8/21.
 */
class VersionInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version_info)
        initView()
    }

    private fun initView() {
        mAboutTv.text = getString(R.string.system_version).plus(getString(R.string.version))
    }

}