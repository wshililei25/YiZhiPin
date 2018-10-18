package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Collect
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.ShopPresenter
import com.yizhipin.goods.presenter.view.ShopView
import com.yizhipin.goods.ui.adapter.ShopVpAdapter
import com.yizhipin.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_shop.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺
 */
class ShopActivity : BaseMvpActivity<ShopPresenter>(), ShopView, View.OnClickListener {

    @Autowired(name = GoodsConstant.KEY_SHOP_ID) //注解接收上个页面的传参
    @JvmField
    var mShopId: Int = 0

    private lateinit var mShopVpAdapter: ShopVpAdapter
    private lateinit var mData: MutableList<Category>
    private lateinit var mShop: Shop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        initView()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mShopVpAdapter = ShopVpAdapter(supportFragmentManager, mShopId.toString())
        mVp.adapter = mShopVpAdapter
        mTab.setupWithViewPager(mVp)
        mBackIv.onClick { finish() }
        mCollectTv.onClick(this)
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mShopId.toString())
        map.put("loginUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getShopDetails(map)
    }

    override fun onGetShopDetailsSuccess(result: Shop) {
        result?.let {
            mShop = it
            mShopNameTv.text = it.shopName
            mShopIv.loadUrl(it.shopImgurl)
            if (it.collection) mCollectTv.setText(getString(R.string.collect_already)) else mCollectTv.setText(getString(R.string.collect_add))
            if (it.shopIdentity == "product") {
                mCategoryTv.text = getString(R.string.hamlet)
            } else if (it.shopIdentity == "homestay") {
                mCategoryTv.text = getString(R.string.stay)
            } else if (it.shopIdentity == "trip") {
                mCategoryTv.text = getString(R.string.group_group)
            } else if (it.shopIdentity == "car") {
                mCategoryTv.text = getString(R.string.motor_homes)
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mCollectTv -> {
                afterLogin {
                    var map = mutableMapOf<String, String>()
                    map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    map.put("shopId", mShop.id.toString())
                    mBasePresenter.collectShop(map)
                }
            }
        }
    }

    /**
     * 收藏成功
     */
    override fun oncollectShopSuccess(result: Collect) {
        mCollectTv.setText(getString(R.string.collect_already))
    }

    /**
     * 取消收藏成功
     */
    override fun onDataIsNull() {
        mCollectTv.setText(getString(R.string.collect_add))
    }

    override fun onComplainShopSuccess(result: Complain) {
    }

    fun getShop(): Shop {
        return mShop
    }
}



