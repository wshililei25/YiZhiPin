package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.injection.component.DaggerCategoryComponent
import com.yizhipin.goods.injection.module.CategoryModule
import com.yizhipin.goods.presenter.UserPresenter
import com.yizhipin.goods.presenter.view.UserView
import com.yizhipin.goods.ui.adapter.CrowdorderAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by ${XiLei} on 2018/8/23.
 * 专员拼单列表
 */
class CrowdorderFragment : BaseMvpFragment<UserPresenter>(), UserView {

    private var mUserId: String = ""
    private lateinit var mGoodsAdapter: CrowdorderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_recyclerview, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        mMultiStateView.startLoading()
        loadData()
    }

    private fun initView() {

        arguments?.let {
            mUserId = arguments!!.getString(GoodsConstant.KEY_USER_ID)
        }
        mRv.layoutManager = LinearLayoutManager(activity)
        mGoodsAdapter = CrowdorderAdapter(activity!!)
        mRv.adapter = mGoodsAdapter
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取拼单列表
     */
    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", mUserId)
        mBasePresenter.getCrowdorderList(map)
    }

    /**
     * 获取拼单列表
     */
    override fun onGetCrowdorderListSuccess(result: MutableList<UserInfo>) {

        if (result != null && result.size > 0) {
            mGoodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onGetUserDetailsSuccess(result: UserInfo) {
    }
}



