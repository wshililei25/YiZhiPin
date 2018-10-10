package com.yizhipin.base.ui.web

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.webkit.ValueCallback
import com.yizhipin.base.R
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.header_bar.*
import kotlinx.android.synthetic.main.layout_activity_web.*
import java.io.File

/**
 * Created by ${XiLei} on 2018/10/6.
 */
class WebViewActivity : BaseActivity(), View.OnClickListener, YZWebView.WebViewInterface {

    val EXTRA_CONTENT = "EXTRA_CONTENT"
    val EXTRA_IMAGE = "EXTRA_IMAGE"
    val EXTRA_IS_SHARE = "EXTRA_IS_SHARE"
    private val mTitle: String? = null
    private val mContent: String? = null
    var mUploadMessage: ValueCallback<Uri>? = null
    var mUploadMessageForAndroid5: ValueCallback<Array<Uri>>? = null
    private val isLogin = false

    companion object {

        val EXTRA_TITLE = "extra_title"
        val EXTRA_URL = "extra_url"

        @JvmField
        val REQUEST_PHONE: Int = 101
        @JvmField
        val FILECHOOSER_RESULTCODE = 5173
        @JvmField
        val FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 5174
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_web)
        setTitle()
        loadPage()
        registerLisenter()
    }

    private fun registerLisenter() {
        mBackIv!!.setOnClickListener(this)
    }

//    @Override
//    public void contentView() {
//        setContentView(R.layout.layout_activity_web);
//    }


    /**
     * 设置当前activity的标题
     */
    private fun setTitle() {
        val title = intent.getStringExtra(EXTRA_TITLE)
        if (!TextUtils.isEmpty(title)) {
            mTitleTv!!.text = title
        }
        /*if (getIntent().getBooleanExtra(EXTRA_IS_SHARE, false)) {
            mAQuery.id(R.id.share_iv).visibility(View.VISIBLE);
        } else {
            mTextViewUpdate.setText(R.string.web_update);
            mTextViewUpdate.setVisibility(View.VISIBLE);
        }*/

    }

    private fun loadPage() {
        val url = intent.getStringExtra(EXTRA_URL)
        //        mYZWebView.loadUrl(StringUtil.ParseURL(url));
        mWebView.loadUrl(url)
        mWebView.setInterfacemy(this)
    }

    override fun onResume() {
        super.onResume()
        setNewUrl()
    }

    /**
     * 刷新web并拼接token
     */
    private fun setNewUrl() {
        /* if (isLogin) {

            String[] strs = mYZWebView.getCurrent().split("token=");
            String newUrl = "";
            if (strs.length > 1) {
                if (TextUtils.isEmpty(strs[1]) || TextUtils.equals("null", strs[1])) {
                    newUrl = strs[0] + "token=" + SharedPreferencesUtil.getYzToken(this);
                } else {
                    newUrl = mYZWebView.getCurrent();
                }
            }
            mYZWebView.loadUrl(StringUtil.ParseURL(newUrl));
            isLogin = false;
        }*/
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.mBackIv -> {
                val isFinish = mWebView.canBack()
                if (isFinish) {
                    finish()
                }
            }
        }

        /* case R.id.mRightTv://刷新
            mYZWebView.loadUrl(mYZWebView.getCurrent());
            break;
        case R.id.share_iv://分享
            mTitle = getIntent().getStringExtra(EXTRA_TITLE);
            mContent = getIntent().getStringExtra(EXTRA_CONTENT);
            mShareManager = new ShareManager(this);
            mShareManager.setShareURL(StringUtil.ParseURL(getIntent().getStringExtra(EXTRA_URL)) + "?shareAction=true");
            mShareManager.setmIageurl(StringUtil.ParseURL(getIntent().getStringExtra(EXTRA_IMAGE)));
            mShareManager.showSharePanel();
            break;*/
    }

    fun setUploadMessageForAndroid5(uploadMessageForAndroid5: ValueCallback<Array<Uri>>) {
        mUploadMessageForAndroid5 = uploadMessageForAndroid5
    }

    fun setUploadMessage(uploadMessage: ValueCallback<Uri>) {
        mUploadMessage = uploadMessage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            //解决Android4.4在webiveiw上传图片闪退问题
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                val realPathFromURI = getRealPathFromURI(data!!.data)
                val file = File(realPathFromURI!!)
                if (file.exists()) {
                    val uri = Uri.fromFile(file)
                    mUploadMessage!!.onReceiveValue(uri)
                }
            } else {
                mUploadMessage!!.onReceiveValue(result)
                mUploadMessage = null
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (null == mUploadMessageForAndroid5)
                return
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            if (result != null) {
                mUploadMessageForAndroid5!!.onReceiveValue(arrayOf(result))
            } else {
                mUploadMessageForAndroid5!!.onReceiveValue(arrayOf())
            }
            mUploadMessageForAndroid5 = null
        }

    }

    /**
     * 获取Uri图片真实路径的方法
     *
     * @param contentUri
     * @return
     */
    fun getRealPathFromURI(contentUri: Uri): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, proj, null, null, null)
        if (cursor.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }

    override fun goLogin() {

    }
}