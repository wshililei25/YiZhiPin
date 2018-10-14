package com.yizhipin.goods.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.Shop
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.activity.ComplainActivity
import com.yizhipin.goods.ui.activity.ShopActivity
import com.yizhipin.provider.common.afterLogin
import kotlinx.android.synthetic.main.fragment_shop_details.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺详情
 */
class ShopDetailsFragment : BaseFragment(), View.OnClickListener {

    private var mShopId: String = ""
    private lateinit var mShop: Shop

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mShop = (activity as ShopActivity).getShop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_shop_details, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setShopData()
    }

    private fun initView() {
        arguments?.let {
            mShopId = arguments!!.getString(GoodsConstant.KEY_SHOP_ID)
        }
        mComplainBtn.onClick(this)
    }

    fun setShopData() {
        Log.d("2", "initObserve")
        mIntroTv.text = mShop.selfCon
        mRegionTv.text = mShop.city
        mDepositTv.text = mShop.bail
        mQualificationTv.text = if (mShop.licenseAuth) getString(R.string.authenticated) else getString(R.string.unverified)
        mCurrentMainTv.text = mShop.scope
        mSellMonthTv.text = mShop.mouthSell
        mSellAllTv.text = mShop.totalSell

        if (mShop.shopIdentity == "product") {
            mTypeTv.text = getString(R.string.hamlet)
        } else if (mShop.shopIdentity == "homestay") {
            mTypeTv.text = getString(R.string.stay)
        } else if (mShop.shopIdentity == "trip") {
            mTypeTv.text = getString(R.string.group_group)
        } else if (mShop.shopIdentity == "car") {
            mTypeTv.text = getString(R.string.motor_homes)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.mComplainBtn -> {
                afterLogin {
                    activity!!.startActivity<ComplainActivity>(GoodsConstant.KEY_SHOP_ID to mShopId)
                }
            }
        }
    }

}



