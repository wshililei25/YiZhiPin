package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.jph.takephoto.model.TResult
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.UploadUtil
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.common.UserConstant
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by ${XiLei} on 2018/7/26.
 * 完善资料
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener, UploadUtil.OnUploadProcessListener {

    private var mRemoteFileUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
    }

    private fun initView() {

        if (intent.getBooleanExtra(UserConstant.KEY_TO_USERINFO, false)) {
            mHeaderBar.getTiTleTv().text = getString(R.string.basis)
            mConfirmBtn.text = getString(R.string.confirm)
            mMobileView.visibility = View.VISIBLE
            mWeChatView.visibility = View.VISIBLE
            mMobileLine.visibility = View.VISIBLE
            mWeChatLine.visibility = View.VISIBLE
            mHeaderBar.getRightTv().visibility = View.GONE
        }

        mUserIconView.onClick(this)
        mHeaderBar.getRightTv().onClick(this)
        mConfirmBtn.onClick(this)
        mMobileView.onClick(this)
        mWeChatView.onClick(this)
        mHeaderBar.getBackIv().onClick(this)
        mConfirmBtn.enable(mNickNameEt, { isBtnEnable() })
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        mPresenter.getUserInfo()
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.mBackIv -> {
                finish()
            }
            R.id.mRightTv -> { //跳过
                finish()
            }
            R.id.mUserIconView -> showAlertView()

            R.id.mConfirmBtn -> {

                var map = mutableMapOf<String, String>()
                map.put("nickname", mNickNameEt.text.toString())
                map.put("imgurl", mRemoteFileUrl)
                map.put("payPwd", "")
                map.put("push", "true")
                map.put("weixin", "")
                map.put("alipay", "")
                map.put("alipayName", "")
                mPresenter.editUserInfo(map)
            }

            R.id.mMobileView -> startActivity<BindMobileActivity>()
            R.id.mWeChatView -> startActivity<BindWeChatActivity>()
        }
    }

    /**
     * 获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {
        if (!mPresenter.checkNetWork()) {
            return
        }
        val localFileUrl = result?.image?.compressPath

        val fileKey = "avatarFile"
        val uploadUtil = UploadUtil.getInstance()
        uploadUtil.setOnUploadProcessListener(this@UserInfoActivity) //设置监听器监听上传状态

        showLoading()
        val filepath = File(localFileUrl)
        uploadUtil.uploadFile(filepath, fileKey, BaseConstant.SERVICE_ADDRESS + "file/img", HashMap<String, String>())
    }

    /**
     * 上传图片成功
     */
    override fun onUploadDone(responseCode: Int, message: String) {
        runOnUiThread {
            hideLoading()
            toast(R.string.upload_success)
            mAddTv.visibility = View.GONE
            mRemoteFileUrl = message
            mUserIconIv.loadUrl(mRemoteFileUrl)
        }
    }

    override fun onUploadProcess(uploadSize: Int) {
    }

    override fun initUpload(fileSize: Int) {
    }

    /**
     * 获取用户信息成功
     */
    override fun getUserResult(result: UserInfo) {

        mRemoteFileUrl = result.imgurl
        mNickNameEt.setText(result.nickname)
        mNickNameEt.setSelection(result.nickname.length)
        if (result.mobile != "") {
            mMobileEt.setText(result.mobile)
            mMobileIv.visibility = View.GONE
            mMobileView.isEnabled = false
        }
        if (result.weixin != "") {
            mWeChatEt.setText(result.mobile)
            mWeChatIv.visibility = View.GONE
            mWeChatView.isEnabled = false
        }
        if (result.imgurl != "") {
            mAddTv.visibility = View.GONE
            mUserIconIv.loadUrl(result.imgurl)
        }

    }

    /**
     * 编辑用户资料成功
     */
    override fun onEditUserResult(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

    private fun isBtnEnable(): Boolean {
        return mConfirmBtn.text.isNullOrEmpty().not()
    }

    override fun onGetCartSuccess(result: Int) {
    }
}
