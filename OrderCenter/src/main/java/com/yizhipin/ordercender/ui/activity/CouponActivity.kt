package com.yizhipin.ordercender.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.CouponPresenter
import com.yizhipin.ordercender.presenter.view.CouponView
import com.yizhipin.ordercender.ui.adapter.CouponAdapter
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_coupon.*

/**
 * Created by ${XiLei} on 2018/9/24.
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_COUPON)
class CouponActivity : BaseMvpActivity<CouponPresenter>(), CouponView, View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @Autowired(name = OrderConstant.KEY_IS_PAY) //注解接收上个页面的传参
    @JvmField
    var mIsPay: Boolean = false

    private var mMaxPage: Int = 1
    private var mCurrentPage: Int = 1
    private lateinit var mCouponAdapter: CouponAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        initView()
        initRefreshLayout()
    }

    private fun initView() {

        mAddressRv.layoutManager = LinearLayoutManager(this)
        mCouponAdapter = CouponAdapter(this)
        mAddressRv.adapter = mCouponAdapter
        mCouponAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Coupon> {
            override fun onItemClick(item: Coupon, position: Int) {
                if (mIsPay) {
                    var intent = Intent()
                    intent.putExtra(OrderConstant.KEY_COUPON_ITEM, item)
                    setResult(ProvideReqCode.CODE_RESULT_COUPON, intent)
                    finish()
                }
            }
        })
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        mRefreshLayout.setPullDownRefreshEnable(false) //禁止下拉刷新
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.yBgGray)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.yBgGray)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("currentPage", mCurrentPage.toString())
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getCouponList(map)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun onCouponListSuccess(result: BasePagingResp<MutableList<Coupon>>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.data != null && result.data!!.size > 0) {
            mMaxPage = result!!.pi.totalPage
            if (mCurrentPage == 1) {
                mCouponAdapter.setData(result.data!!)
            } else {
                mCouponAdapter.dataList.addAll(result.data!!)
                mCouponAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 加载更多
     */
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }
    }

    /**
     * 下拉刷新
     */
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }
}