package com.yizhipin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseApplication.Companion.context
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.ShareBill
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.ShareBillPresenter
import com.yizhipin.goods.presenter.view.ShareBillView
import com.yizhipin.goods.ui.adapter.ShareBillAdapter
import kotlinx.android.synthetic.main.activity_share_bill_list.*


/**
 * Created by ${XiLei} on 2018/8/23.
 */
class ShareBillListActivity : BaseMvpActivity<ShareBillPresenter>(), ShareBillView {

    @Autowired(name = GoodsConstant.KEY_GOODS_ID) //注解接收上个页面的传参
    @JvmField
    var mGoodsId: Int = 0

    private lateinit var mCartAdapter: ShareBillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_bill_list)
        initView()
    }

    private fun initView() {
        mCartRv.layoutManager = LinearLayoutManager(this)
        mCartAdapter = ShareBillAdapter(context!!)
        mCartRv.adapter = mCartAdapter
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        var map = mutableMapOf<String, String>()
        map.put("lng", "0")
        map.put("lat", "0")
        map.put("pid", mGoodsId.toString())
        mBasePresenter.getShareBillList(map)
    }

    override fun onGetShareBillListSuccess(result: MutableList<ShareBill>) {
        if (result != null && result.size > 0) {
            mCartAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}



