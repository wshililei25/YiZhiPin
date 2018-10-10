package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.activity_wallet.*

/**
 * Created by ${XiLei} on 2018/9/24.
 *
 * 钱包
 */
class WalletActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        initView()
    }

    private fun initView() {
        mRechargeTv.onClick(this)
        mWithdrawTv.onClick(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRechargeTv -> ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_RECHARGE).navigation()
        }
        when (v.id) {
            R.id.mWithdrawTv -> ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_WITHDRAW).navigation()
        }
    }


}