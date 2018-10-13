package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.ShopPresenter
import com.yizhipin.goods.presenter.view.ShopView
import com.yizhipin.goods.ui.adapter.ShopVpAdapter
import kotlinx.android.synthetic.main.activity_shop.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺
 */
class ShopActivity : BaseMvpActivity<ShopPresenter>(), ShopView {

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
        loadData()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mShopVpAdapter = ShopVpAdapter(supportFragmentManager, mShopId.toString())
        mVp.adapter = mShopVpAdapter
        mTab.setupWithViewPager(mVp)
        mBackIv.onClick { finish() }
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mShopId.toString())
        mBasePresenter.getShopDetails(map)
    }

    override fun onGetShopDetailsSuccess(result: Shop) {
        result?.let {
            mShop = result
            mShopNameTv.text = result.shopName
            mShopIv.loadUrl(result.shopImgurl)
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

    override fun onComplainShopSuccess(result: Complain) {
    }

    fun getShop(): Shop {
        return mShop
    }
}



