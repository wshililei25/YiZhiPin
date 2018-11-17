package com.yizhipin.generalizecenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.data.response.GeneralizeInvest
import com.yizhipin.generalizecenter.data.response.GeneralizeInvestAmount
import com.yizhipin.generalizecenter.data.response.InvestDetails
import com.yizhipin.generalizecenter.data.response.InvestList
import com.yizhipin.generalizecenter.presenter.view.GeneralizeInvestView
import com.yizhipin.generalizecenter.ui.adapter.GenInvestVpAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizeInvestPresenter
import kotlinx.android.synthetic.main.activity_generalize_invest.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/25.
 * 投资推广
 */
class GeneralizeInvestActivity : BaseMvpActivity<GeneralizeInvestPresenter>(), GeneralizeInvestView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generalize_invest)

        initView()
        loadData()
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_SCROLLABLE
        mOrderVp.adapter = GenInvestVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mHeaderBar.getRightTv().onClick {
            startActivity<InvestListActivity>()
        }

//        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, 0)
    }


    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getInvestStatistics(map)
    }

    override fun onGetInvestAmountSuccess(result: GeneralizeInvestAmount) {
        with(result) {
            mMounthIncomeTv.text = getString(R.string.rmb).plus(month)
            mAllIncomeTv.text = getString(R.string.rmb).plus(total)
        }
    }

    override fun onGetInvestListSuccess(result: MutableList<GeneralizeInvest>) {
    }

    override fun onGetInvestDetailsListSuccess(result: MutableList<InvestList>) {
    }
    override fun onGetInvestDetailsSuccess(result: InvestDetails) {
    }
}