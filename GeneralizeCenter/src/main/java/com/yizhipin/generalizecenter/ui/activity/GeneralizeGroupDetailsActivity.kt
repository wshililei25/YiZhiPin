package com.yizhipin.generalizecenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseApplication.Companion.context
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.generalizecenter.R
import com.yizhipin.generalizecenter.common.GeneralizeConstant
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.data.response.GeneralizeGroupDetails
import com.yizhipin.generalizecenter.data.response.GeneralizeUsers
import com.yizhipin.generalizecenter.presenter.view.GeneralizeView
import com.yizhipin.generalizecenter.ui.adapter.GeneralizeUsersAdapter
import com.yizhipin.goods.injection.component.DaggerGeneralizeComponent
import com.yizhipin.goods.injection.module.GeneralizeModule
import com.yizhipin.goods.presenter.GeneralizePresenter
import com.yizhipin.goods.ui.adapter.EvaluateImageAdapter
import kotlinx.android.synthetic.main.activity_generalize_group_details.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/23.
 * 投资团详情
 */
class GeneralizeGroupDetailsActivity : BaseMvpActivity<GeneralizePresenter>(), GeneralizeView, View.OnClickListener {

    @Autowired(name = GeneralizeConstant.KEY_GEN_GROUP_ID) //注解接收上个页面的传参
    @JvmField
    var mId: String = ""

    private lateinit var mGoodsAdapter: GeneralizeUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generalize_group_details)
        initView()
        loadData()
    }

    private fun initView() {
        mBtn.onClick(this)
        mRv.layoutManager = LinearLayoutManager(this!!)
        mGoodsAdapter = GeneralizeUsersAdapter(this!!)
        mRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GeneralizeUsers> {
            override fun onItemClick(item: GeneralizeUsers, position: Int) {
                //                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.product.id!!)
            }
        })
    }

    override fun injectComponent() {
        DaggerGeneralizeComponent.builder().activityComponent(mActivityComponent).generalizeModule(GeneralizeModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mId.toString())
        mBasePresenter.getGenGroupDetails(map)
    }

    /**
     * 获取商品列表成功
     */
    override fun onGetGroupDetailsSuccess(result: GeneralizeGroupDetails) {
        result?.let {

            with(result) {
                mGroupNameTv.text = title
                mPeopleCountTv.text = "${personCount}人出资共"
                mAmountTv.text = getString(R.string.rmb).plus(amount)

                mUserIconIv.loadUrl(imgurl)
                mStartAmountTv.text = getString(R.string.rmb).plus(amount)
                mContentTv.text = content
                mMobileTv.text = nickname
                mDateTv.text = "${createTime}最新出价"
                if (imgurls.isNotEmpty()) {
                    mImageRv.visibility = View.VISIBLE
                    mImageRv.layoutManager = GridLayoutManager(context, 3)
                    var mEvaluateImageAdapter = EvaluateImageAdapter(context)
                    mImageRv.adapter = mEvaluateImageAdapter
                    val list = imgurls.split(",").toMutableList()
                    mEvaluateImageAdapter.setData(list)
                }

                mGoodsAdapter.setData(users)
            }

        }
    }

    //
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

    override fun onGetGoodsDetailsSuccess(result: GeneralizeCollect) {
    }

    override fun onGetEndTimeSuccess(result: String) {
    }
}



