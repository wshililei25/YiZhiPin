package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.activity_bind_wechat.*


/**
 * Created by ${XiLei} on 2018/8/5.
 * 绑定微信
 */
class BindWeChatActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bind_wechat)
        initView()
    }

    private fun initView() {
        mBindBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.mBindBtn -> {

            }
        }
    }

}