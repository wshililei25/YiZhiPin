package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.widgets.PayPasswordView
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.PayPwdPresenter
import com.yizhipin.usercenter.presenter.view.PayPwdView
import kotlinx.android.synthetic.main.activity_pay_pwd_set.*
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 支付密码设置
 */

@Route(path = RouterPath.UserCenter.SET_PAY_PWD)
class PayPwdSetActivity : BaseMvpActivity<PayPwdPresenter>(), PayPwdView, View.OnClickListener {

    private var mPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pwd_set)

        initView()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mPayView.setPayPasswordEndListener(object : PayPasswordView.PayEndListener {
            override fun doEnd(password: String?) {
                mPassword = password!!
                mPayBtn.isEnabled = true
            }

        })

        mPayBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPayBtn -> {

                var map = mutableMapOf<String, String>()
                map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("payPwd", mPassword)

                mBasePresenter.setPayPwd(map)
            }
        }
    }

    override fun onSetPayPwdSuccess(result: Boolean) {
        toast("设置成功")
        AppPrefsUtils.putString(ProviderConstant.KEY_PAY_PWD, mPassword ?: "")
        finish()
    }

}