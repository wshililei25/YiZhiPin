package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
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
import kotlinx.android.synthetic.main.activity_pay_pwd_update.*
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 支付密码修改
 */

@Route(path = RouterPath.UserCenter.UPDATE_PAY_PWD)
class PayPwdUpdateActivity : BaseMvpActivity<PayPwdPresenter>(), PayPwdView, View.OnClickListener {

    private var mPassword = ""
    private var mPasswordNew = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pwd_update)

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
                if (mPassword.isNotEmpty() && mPasswordNew.isNotEmpty()) {
                    mPayBtn.isEnabled = true
                }
            }

        })
        mPayNewView.setPayPasswordEndListener(object : PayPasswordView.PayEndListener {
            override fun doEnd(password: String?) {
                mPasswordNew = password!!
                if (mPassword.isNotEmpty() && mPasswordNew.isNotEmpty()) {
                    mPayBtn.isEnabled = true
                }
            }

        })

        mPayBtn.onClick(this)
        mForgetPwdTv.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPayBtn -> {

                if (mPassword.isNullOrEmpty()) {
                    toast("原密码不能空")
                    return
                }
                if (mPasswordNew.isNullOrEmpty()) {
                    toast("新密码不能空")
                    return
                }

                var map = mutableMapOf<String, String>()
                map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("oldPayPwd", mPassword)
                map.put("payPwd", mPasswordNew)

                mBasePresenter.updatePayPwd(map)
            }
            R.id.mForgetPwdTv -> {
                ARouter.getInstance().build(RouterPath.UserCenter.RESET_PAY_PWD).navigation()
            }
        }
    }

    override fun onSetPayPwdSuccess(result: Boolean) {
        toast("修改成功")
        AppPrefsUtils.putString(ProviderConstant.KEY_PAY_PWD, mPasswordNew ?: "")
        finish()
    }

}