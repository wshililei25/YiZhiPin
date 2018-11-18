package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.UserPresenter
import com.yizhipin.goods.presenter.view.UserView
import com.yizhipin.goods.ui.adapter.UserVpAdapter
import kotlinx.android.synthetic.main.activity_user.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 用户
 */
class UserActivity : BaseMvpActivity<UserPresenter>(), UserView, View.OnClickListener {

    @Autowired(name = GoodsConstant.KEY_USER_ID) //注解接收上个页面的传参
    @JvmField
    var mUid: Int = 0

    private lateinit var mShopVpAdapter: UserVpAdapter
    private lateinit var mData: MutableList<Category>
    private lateinit var mShop: Shop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initView()
    }

    private fun initView() {
        mTab.tabMode = TabLayout.MODE_FIXED
        mShopVpAdapter = UserVpAdapter(supportFragmentManager, mUid.toString())
        mVp.adapter = mShopVpAdapter
        mTab.setupWithViewPager(mVp)
        mBackIv.onClick { finish() }
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
        map.put("id", mUid.toString())
        map.put("loginUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getUserDetails(map)
    }

    override fun onGetUserDetailsSuccess(result: UserInfo) {
        with(result) {
            mShopNameTv.text = nickname
            mCreditTv.text = score
            mLumpTv.text = baiPin
            mShopIv.loadUrl(imgurl)
            if (commissioner) mCategoryTv.setVisible(true) else mCategoryTv.setVisible(false)

            when (level) {
                1 -> mGradeIv.setImageResource(R.drawable.grade1)
                2 -> mGradeIv.setImageResource(R.drawable.grade2)
                3 -> mGradeIv.setImageResource(R.drawable.grade3)
                4 -> mGradeIv.setImageResource(R.drawable.grade4)
                5 -> mGradeIv.setImageResource(R.drawable.grade5)
                6 -> mGradeIv.setImageResource(R.drawable.grade6)
                7 -> mGradeIv.setImageResource(R.drawable.grade7)
                8 -> mGradeIv.setImageResource(R.drawable.grade8)
                9 -> mGradeIv.setImageResource(R.drawable.grade9)
                10 -> mGradeIv.setImageResource(R.drawable.grade10)
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    fun getShop(): Shop {
        return mShop
    }

    override fun onGetCrowdorderListSuccess(result: MutableList<UserInfo>) {
    }
}



