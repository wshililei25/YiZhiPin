package com.yizhipin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.yizhipin.R
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/21.
 */
class SettingActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        mToggleButton.isChecked = AppPrefsUtils.getBoolean(ProviderConstant.KEY_IS_PUSH)
        mToggleButton.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, true)
                } else {
                    AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, false)
                }
            }

        })
        mAboutTv.onClick(this)
        mLogoutBtn.onClick(this)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.mAboutTv -> startActivity<VersionInfoActivity>()
            R.id.mLogoutBtn -> {
                UserPrefsUtils.putUserInfo(null)
                finish()
            }
        }
    }
}