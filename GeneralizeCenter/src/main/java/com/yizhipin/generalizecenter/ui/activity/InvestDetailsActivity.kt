package com.yizhipin.generalizecenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.data.response.*
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeConsortiumAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizeInvestPresenter
import kotlinx.android.synthetic.main.activity_invest_details.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 * 投资详情
 */
class InvestDetailsActivity : BaseMvpActivity<GeneralizeInvestPresenter>(), GeneralizeInvestView, View.OnClickListener {

    @Autowired(name = GeneralizeConstant.KEY_INVEST_ID) //注解接收上个页面的传参
    @JvmField
    var mInvestId = ""

    private lateinit var mGoodsAdapter: GeneralizeConsortiumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest_details)
        initView()
        loadData()
    }

    private fun initView() {
        mConsortiumRv.layoutManager = LinearLayoutManager(this)
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
        map.put("investmentId", mInvestId.toString())
        mBasePresenter.getInvestDetails(map)
    }

    /**
     * 获取商品列表成功
     */
    override fun onGetInvestDetailsSuccess(result: InvestDetails) {
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

//            mGoodsAdapter.setData(result.groups)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
        }
    }

    override fun onGetInvestAmountSuccess(result: GeneralizeInvestAmount) {
    }

    override fun onGetInvestListSuccess(result: MutableList<GeneralizeInvest>) {
    }

    override fun onGetInvestDetailsListSuccess(result: MutableList<InvestList>) {
    }
}



