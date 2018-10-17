package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.jph.takephoto.model.TResult
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.UploadUtil
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Complain
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


    @Autowired(name = GoodsConstant.KEY_SHOP_ID) //注解接收上个页面的传参
    @JvmField
    var mShopId: String = ""

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
        var map = mutableMapOf<String, String>()
        map.put("id", mShopId)
        mPresenter.getShopDetails(map)
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
                var imgurls = ""
                for (list in mComplainImageAdapter.dataList) {
                    imgurls += list.plus(",")
                }
                imgurls = if (imgurls.isNullOrEmpty()) "" else imgurls.subSequence(0, imgurls.length - 1).toString()
                var map = mutableMapOf<String, String>()
                map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("shopId", mShopId)
                map.put("content", mEt.text.toString())
                map.put("imgurls", imgurls)
                mPresenter.getComplainShop(map)
            }
        }
    }

    /**
     * 举报投诉成功
     */
    override fun onComplainShopSuccess(result: Complain) {
        toast("投诉成功")
        finish()
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

        if (!mPresenter.checkNetWork()) {
            return
        }
        for (list in result!!.images) {
            val localFileUrl = list.compressPath
            val fileKey = "avatarFile"
            val uploadUtil = UploadUtil.getInstance()
            uploadUtil.setOnUploadProcessListener(this@ComplainActivity) //设置监听器监听上传状态

            showLoading()
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
            ToastUtils.INSTANCE.showToast(this,R.string.upload_success)
            mComplainImageAdapter.dataList.add(message)
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

    /**
     * 获取店铺信息成功
     */
    override fun onGetShopDetailsSuccess(result: Shop) {
        result?.let {
            mShopNameTv.text = result.shopName
//            mShopIv.loadUrl(result.shopImgurl)
            if (result.shopIdentity == "product") {
                mCategoryTv.text = getString(R.string.hamlet)
            } else if (result.shopIdentity == "homestay") {
                mCategoryTv.text = getString(R.string.stay)
            } else if (result.shopIdentity == "trip") {
                mCategoryTv.text = getString(R.string.group_group)
            } else if (result.shopIdentity == "car") {
                mCategoryTv.text = getString(R.string.motor_homes)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastUtils.INSTANCE.cancelToast()//销毁页面时，取消掉toast
    }
    override fun oncollectShopSuccess(result: Collect) {
    }

}
