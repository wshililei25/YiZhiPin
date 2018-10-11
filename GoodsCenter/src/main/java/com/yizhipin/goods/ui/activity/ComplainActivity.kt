package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.jph.takephoto.model.TResult
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.UploadUtil
import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.Shop
import com.yizhipin.goods.event.ImageMoreEvent
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.ShopPresenter
import com.yizhipin.goods.presenter.view.ShopView
import com.yizhipin.goods.ui.adapter.ComplainImageAdapter
import kotlinx.android.synthetic.main.activity_complain.*
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by ${XiLei} on 2018/7/26.
 * 投诉举报
 */
class ComplainActivity : BaseTakePhotoActivity<ShopPresenter>(), ShopView, View.OnClickListener, UploadUtil.OnUploadProcessListener {

//    private var mImageList = mutableListOf<String>()
    private var mRemoteFileUrl: String = ""
    private lateinit var mComplainImageAdapter: ComplainImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complain)

        initView()
        initObserve()
        initData()
    }

    private fun initView() {
        mConfirmBtn.onClick(this)
        mConfirmBtn.enable(mEt, { isBtnEnable() })

        mRv.layoutManager = GridLayoutManager(this, 4)
        mComplainImageAdapter = ComplainImageAdapter(this)
        mRv.adapter = mComplainImageAdapter
    }

    private fun initData() {
//        mPresenter.getUserInfo()
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.mConfirmBtn -> {
                Log.d("2", "mComplainImageAdapter=" + mComplainImageAdapter.dataList.size)
            }
        }
    }

    private fun initObserve() {
        Bus.observe<ImageMoreEvent>()
                .subscribe { t: ImageMoreEvent ->
                    run {
                        showAlertViewMore()
                    }
                }.registerInBus(this)
    }

    /**
     * 获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {

        showLoading()
        for (list in result!!.images) {
            val localFileUrl = list.compressPath
            val fileKey = "avatarFile"
            val uploadUtil = UploadUtil.getInstance()
            uploadUtil.setOnUploadProcessListener(this@ComplainActivity) //设置监听器监听上传状态

            val filepath = File(localFileUrl)
            uploadUtil.uploadFile(filepath, fileKey, BaseConstant.SERVICE_ADDRESS + "file/img", HashMap<String, String>())
        }
    }

    /**
     * 上传图片成功
     */
    override fun onUploadDone(responseCode: Int, message: String) {
        runOnUiThread {
            hideLoading()
            toast(R.string.upload_success)
            mComplainImageAdapter.dataList.add(message)
            Log.d("2", "message=" + message)
            Log.d("2", "mComplainImageAdapter.dataList=" + mComplainImageAdapter.dataList.size)
            mComplainImageAdapter.setData(mComplainImageAdapter.dataList)
        }
    }

    override fun onUploadProcess(uploadSize: Int) {
    }

    override fun initUpload(fileSize: Int) {
    }


    private fun isBtnEnable(): Boolean {
        return mConfirmBtn.text.isNullOrEmpty().not()
    }

    override fun onGetShopDetailsSuccess(result: Shop) {
    }
}
