package com.yizhipin.generalizecenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeCollectGroup
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
import com.yizhipin.generalizecenter.presenter.view.GeneralizeView
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeConsortiumAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizePresenter
import kotlinx.android.synthetic.main.activity_generalize_details.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class GeneralizeDetailsActivity : BaseMvpActivity<GeneralizePresenter>(), GeneralizeView, View.OnClickListener {

    @Autowired(name = GeneralizeConstant.KEY_GEN_ID) //注解接收上个页面的传参
    @JvmField
    var mId: Int = 0

    private lateinit var mGoodsAdapter: GeneralizeConsortiumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generalize_details)
        initView()
        loadData()
    }

    private fun initView() {
        mPersonageBtn.onClick(this)
        mConsortiumRv.layoutManager = LinearLayoutManager(this!!)
        mGoodsAdapter = GeneralizeConsortiumAdapter(this!!)
        mConsortiumRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GeneralizeCollectGroup> {
            override fun onItemClick(item: GeneralizeCollectGroup, position: Int) {
                startActivity<GeneralizeGroupDetailsActivity>(GeneralizeConstant.KEY_GEN_GROUP_ID to item.id)
            }
        })
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        map.put("id", mId.toString())
        mBasePresenter.getGenBiddingDetails(map)
    }

    /**
     * 获取商品列表成功
     */
    override fun onGetGoodsDetailsSuccess(result: GeneralizeCollect) {
        result?.let {
            mGoodsNameTv.text = result.product.name
            mSystemTv.text = result.product.pinPrice.toString()
            mRetailTv.text = result.product.price.toString()
            mShopTv.text = result.product.shop!!.shopName
            mGoodsIv.loadUrl(result.product.imgurl!!)

//            mMobileTv.text = StringUtils.setMobileStar(result.max.name)
            mMobileTv.text = result.max.name
            mDateTv.text = "${result.max.date}最新出价"
            mAmountTv.text = getString(R.string.rmb).plus(result.max.amount)
            mUserIconIv.loadUrl(result.max.imgurl)

            when (result.product.shop!!.shopIdentity) {
                "product" -> mTypeTv.text = getString(R.string.hamlet)
                "homestay" -> mTypeTv.text = getString(R.string.stay)
                "trip" -> mTypeTv.text = getString(R.string.group_group)
                "car" -> mTypeTv.text = getString(R.string.motor_homes)
            }

            mGoodsAdapter.setData(result.groups)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPersonageBtn -> {
                startActivity<PayGeneralizeActivity>(GeneralizeConstant.KEY_GEN_ID to mId)
            }
        }
    }

    override fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<GeneralizeCollect>>) {
    }

    override fun onPayPersonageSuccess(result: String) {
    }

    override fun onGetGroupDetailsSuccess(result: GeneralizeGroupDetails) {
    }

    override fun onGetEndTimeSuccess(result: String) {
    }
}



