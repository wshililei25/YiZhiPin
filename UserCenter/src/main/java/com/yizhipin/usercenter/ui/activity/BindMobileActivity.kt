package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.BindMobilePresenter
import com.yizhipin.usercenter.presenter.view.BindMobileView
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by ${XiLei} on 2018/8/5.
 * 绑定手机号
 */
class BindMobileActivity : BaseMvpActivity<BindMobilePresenter>(), BindMobileView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bind_mobile)
        initView()
    }

    private fun initView() {
        mSendCodeTv.onClick(this)
        mLoginBtn.onClick(this)
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mCodeEt, { isBtnEnable() })
    }

    override fun onClick(v: View) {
        when (v.id) {
        /* R.id.mSendCodeTv -> {
             mBasePresenter.login(mMobileEt.text.toString(), mCodeEt.text.toString(), "user")
         }*/

            R.id.mLoginBtn -> {
                var map = mutableMapOf<String, String>()
                map.put("mobile", mMobileEt.text.toString())
                map.put("smsCode", mCodeEt.text.toString())
                mBasePresenter.bindMobile(map)
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mCodeEt.text.isNullOrEmpty().not()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 绑定手机号成功
     */
    override fun onBindMobileResult(result: Boolean) {
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, mMobileEt.text.toString())
        finish()
    }

}