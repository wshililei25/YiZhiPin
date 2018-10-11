package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.activity.ComplainActivity
import com.yizhipin.provider.common.afterLogin
import kotlinx.android.synthetic.main.fragment_shop_details.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 * 店铺详情
 */
class ShopDetailsFragment : BaseFragment(), View.OnClickListener {

    private var mShopId: String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_shop_details, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        arguments?.let {
            mShopId = arguments!!.getString(GoodsConstant.KEY_SHOP_ID)
        }
        mComplainBtn.onClick(this)
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



