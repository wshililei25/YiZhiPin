package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.jph.takephoto.model.TResult
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.UploadUtil
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.presenter.CommissionerPresenter
import kotlinx.android.synthetic.main.activity_commissioner_apply.*
import org.jetbrains.anko.toast
import java.io.File


/**
 * Created by ${XiLei} on 2018/9/24.
 *
 * 申请专员
 */
class CommissionerApplyActivity : BaseTakePhotoActivity<CommissionerPresenter>(), View.OnClickListener, UploadUtil.OnUploadProcessListener {

    private var mFrontFileUrl: String = "" //正面照
    private var mContraryFileUrl: String = "" //反面照
    private var mHandFileUrl: String = "" //手持照片
    private var mType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commissioner_apply)

        initView()
    }

    private fun initView() {
        mCardFrontIv.onClick(this)
        mCardContraryIv.onClick(this)
        mHandIv.onClick(this)
        mCommitBtn.onClick(this)
        mCommitBtn.enable(mNameEt, { isBtnEnable() })
        mCommitBtn.enable(mIdEt, { isBtnEnable() })
    }

    override fun injectComponent() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mCardFrontIv -> {
                mType = 1
                showAlertView()
            }
            R.id.mCardContraryIv -> {
                mType = 2
                showAlertView()
            }
            R.id.mHandIv -> {
                mType = 3
                showAlertView()
            }
            R.id.mCommitBtn -> {
                if (mFrontFileUrl.isNullOrEmpty() || mContraryFileUrl.isNullOrEmpty() || mHandFileUrl.isNullOrEmpty()) {
                    toast("请上传照片")
                    return
                }
            }
        }
    }

    /**
     * 获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {

        val localFileUrl = result?.image?.compressPath

        val fileKey = "avatarFile"
        val uploadUtil = UploadUtil.getInstance()
        uploadUtil.setOnUploadProcessListener(this@CommissionerApplyActivity) //设置监听器监听上传状态

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
            ToastUtils.INSTANCE.showToast(this, getString(R.string.upload_success));
            when (mType) {
                1 -> {
                    mFrontFileUrl = message
                    mCardFrontIv.loadUrl(mFrontFileUrl)
                }
                2 -> {
                    mContraryFileUrl = message
                    mCardContraryIv.loadUrl(mContraryFileUrl)
                }
                3 -> {
                    mHandFileUrl = message
                    mHandIv.loadUrl(mHandFileUrl)
                }
            }
        }
    }

    override fun onUploadProcess(uploadSize: Int) {
    }

    override fun initUpload(fileSize: Int) {
    }

    private fun isBtnEnable(): Boolean {
        return mNameEt.text.isNullOrEmpty().not() &&
                mIdEt.text.isNullOrEmpty().not() &&
                mFrontFileUrl.isNullOrEmpty()
    }


}