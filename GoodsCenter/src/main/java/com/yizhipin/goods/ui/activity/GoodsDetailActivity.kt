package com.yizhipin.goods.ui.activity

import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.GoodsCollect
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.DateUtils
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.base.widgets.BannerImageLoader
import com.yizhipin.base.widgets.IdeaScrollView
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.GoodsDetailPresenter
import com.yizhipin.goods.presenter.view.GoodsDetailView
import com.yizhipin.goods.ui.adapter.EvaluateImageAdapter
import com.yizhipin.provider.common.afterLogin
import com.yizhipin.provider.router.RouterPath
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_good_details.*
import kotlinx.android.synthetic.main.layout_evaluate_item.*
import kotlinx.android.synthetic.main.layout_report_item.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class GoodsDetailActivity : BaseMvpActivity<GoodsDetailPresenter>(), GoodsDetailView, View.OnClickListener {

    @Autowired(name = GoodsConstant.KEY_GOODS_ID) //注解接收上个页面的传参
    @JvmField
    var mGoodsId: Int = 0

    private lateinit var mQBadgeView: QBadgeView

    private var mCurrentPercentage = 0f
    private var isNeedScrollTo = true
    private lateinit var mEvaluateImageAdapter: EvaluateImageAdapter
    private lateinit var mGoods: Goods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_details)

        initScrollView()
        initView()
        initSrarView()
        initBanner()
        loadEvaluateData()
        loadReportData()
        loadCartSize()
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initScrollView() {

        val rectangle = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        mIdeaScrollView.setViewPager(mBanner, getMeasureHeight(mHeaderParent) - rectangle.top)
        mBackHeadIv.alpha = 0f
        mRadioGroup.alpha = 0f
        mRadioGroup.check(mRadioGroup.getChildAt(0).id)

        var araryDistance = arrayListOf<Int>()
        araryDistance.add(0)
        araryDistance.add(getMeasureHeight(mGoodView) - getMeasureHeight(mHeaderParent))
        araryDistance.add(getMeasureHeight(mGoodView) + getMeasureHeight(mEvaluateView) - getMeasureHeight(mHeaderParent))
        araryDistance.add(getMeasureHeight(mGoodView) + getMeasureHeight(mEvaluateView) + getMeasureHeight(mReportView) - getMeasureHeight(mHeaderParent))
        mIdeaScrollView.arrayDistance = araryDistance
        mIdeaScrollView.setOnScrollChangedColorListener(object : IdeaScrollView.OnScrollChangedColorListener {
            override fun onChanged(percentage: Float) {
                mRadioGroup.setBackgroundResource(R.color.yBgGray)
                mRadioGroup.setAlpha((if (percentage > 0.9f) 1.0f else percentage) * 255)
                mBackHeadIv.setAlpha((if (percentage > 0.9f) 1.0f else percentage) * 255)
                setRadioButtonTextColor(percentage)
            }

            override fun onChangedFirstColor(percentage: Float) {
            }

            override fun onChangedSecondColor(percentage: Float) {
            }

        })
        mIdeaScrollView.setOnSelectedIndicateChangedListener(object : IdeaScrollView.OnSelectedIndicateChangedListener {
            override fun onSelectedChanged(position: Int) {
                isNeedScrollTo = false
                mRadioGroup.check(mRadioGroup.getChildAt(position).getId())
                isNeedScrollTo = true
            }

        })
        mRadioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                for (i in 0 until mRadioGroup.getChildCount()) {
                    val radioButton = mRadioGroup.getChildAt(i) as RadioButton
                    radioButton.setTextColor(if (radioButton.isChecked) resources.getColor(R.color.yBlack) else resources.getColor(R.color.yHint))
                    if (radioButton.isChecked && isNeedScrollTo) {
                        mIdeaScrollView.setPosition(i)
                    }
                }
            }

        })
    }

    private fun initView() {

        mQBadgeView = QBadgeView(this)
        retailRmb.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        retailRmb.paint.isAntiAlias = true
        mRetailPriceTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        mRetailPriceTv.paint.isAntiAlias = true

        mBackIv.onClick { finish() }
        mBackHeadIv.onClick { finish() }
        mEvaluateMoreTv.onClick(this)
        mReportMoreTv.onClick(this)
        mShopView.onClick(this)
        mSingleBuyView.onClick(this)
        mAddCartBtn.onClick(this)
        mCollectionTv.onClick(this)
        mQBadgeView.onClick {
            afterLogin {
                var map = mutableMapOf<String, String>()
                map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("productId", mGoods.id.toString())
                map.put("count", "1")
                mBasePresenter.addCart(map)
            }
        }
    }

    private fun initSrarView() {
        mStarView.refreshView()
    }

    private fun initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    private fun setRadioButtonTextColor(percentage: Float) {
        if (Math.abs(percentage - mCurrentPercentage) >= 0.1f) {
            for (i in 0 until mRadioGroup.getChildCount()) {
                val radioButton = mRadioGroup.getChildAt(i) as RadioButton
                radioButton.setTextColor(if (radioButton.isChecked) resources.getColor(R.color.yBlack) else resources.getColor(R.color.yHint))
            }
            this.mCurrentPercentage = percentage
        }
    }

    private fun getMeasureHeight(view: View): Int {
        val width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        return view.measuredHeight
    }

    /**
     * 设置购物车数量
     */
    private fun loadCartSize() {
        if (AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE) > 0) {
            mQBadgeView.badgeGravity = Gravity.END or Gravity.TOP
            mQBadgeView.setGravityOffset(26f, 2f, true)
            mQBadgeView.setBadgeTextSize(6f, true)
            mQBadgeView.bindTarget(mAddCartBtn).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.mAddCartBtn -> {
                afterLogin {
                    var map = mutableMapOf<String, String>()
                    map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    map.put("productId", mGoods.id.toString())
                    map.put("count", "1")
                    mBasePresenter.addCart(map)
                }
            }

            R.id.mCollectionTv -> { //收藏商品
                afterLogin {
                    var map = mutableMapOf<String, String>()
                    map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    map.put("pid", mGoods.id.toString())
                    mBasePresenter.collectGood(map)
                }
            }
            R.id.mEvaluateMoreTv -> startActivity<EvaluateActivity>(GoodsConstant.KEY_GOODS_ID to mGoodsId
                    , GoodsConstant.KEY_EVA_COUNT to mGoods!!.evaCount)

            R.id.mReportMoreTv -> startActivity<ReportActivity>(GoodsConstant.KEY_GOODS_ID to mGoodsId, GoodsConstant.KEY_EVA_COUNT to mGoods!!.experienceCount)

            R.id.mShopView -> startActivity<ShopActivity>(GoodsConstant.KEY_SHOP_ID to mGoods!!.shop!!.id)

            R.id.mSingleBuyView -> { //单价买
                afterLogin {
                    mGoods?.let {
                        var list = arrayListOf<Goods>()
                        list.add(mGoods)
                        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_DETAILS)
                                .withBoolean(BaseConstant.KEY_IS_PIN, false)
                                .withParcelableArrayList(BaseConstant.KEY_GOODS_LIST, list)
                                .navigation()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadGoodDetailsData()
    }

    /**
     * 商品详情
     */
    private fun loadGoodDetailsData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mGoodsId.toString())
        map.put("loginUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mBasePresenter.getGoodsDetail(map)
    }

    /**
     * 获取商品详情成功
     */
    override fun onGetGoodsDetailSuccess(result: Goods) {
        result?.let {
            mGoods = result
            mGoodName.text = result.name
            mSystemPriceTv.text = result.pinPrice.toString()
            mRetailPriceTv.text = result.price.toString()
            mInventoryTv.text = "${result.count}件"
            mMonthVolumeTv.text = "月销  ${result.monthSellerCount}单"
            mAllVolumeTv.text = "总成交  ${result.totalCount}单"
            mStarView.setCheckStarCount(result.starCount!!)
            mShopName.text = result.shop!!.shopName
            mEvaluateTv.text = "${getString(R.string.evaluate_new)} (${result.evaCount})"
            mReportTv.text = "${getString(R.string.report_commissioner)} (${result.experienceCount})"
            mShopIv.loadUrl(result.shop!!.shopImgurl)
            mSystemPriceBotTv.text = getString(R.string.rmb).plus(result.pinPrice.toString())
            mSingleBuyTv.text = getString(R.string.rmb).plus(result.price.toString())
            if (result.collection) mCollectionTv.setText(getString(R.string.collect_already)) else mCollectionTv.setText(getString(R.string.collect_goods))


            when(result.shop!!.shopIdentity){
                "product" ->  mCategoryTv.text = getString(R.string.hamlet)
                "homestay" ->  mCategoryTv.text = getString(R.string.stay)
                "trip" ->  mCategoryTv.text = getString(R.string.group_group)
                "car" ->  mCategoryTv.text = getString(R.string.motor_homes)
            }
            
            result.banner?.let {
                val list = result.banner!!.split(",").toMutableList()
                mBanner.setImages(list)
                mBanner.start()
            }
        }
    }

    /**
     * 最新评价
     */
    private fun loadEvaluateData() {
        var map = mutableMapOf<String, String>()
        map.put("pid", mGoodsId.toString())
        mBasePresenter.getEvaluateNew(map)
    }

    /**
     * 获取最新评价成功
     */
    override fun onGetEvaluateNewSuccess(result: Evaluate) {
        result?.let {
            mPhoneTv.text = StringUtils.setMobileStar(result.nickname)
            mDateTv.text = DateUtils.parseDate(result.createTime, DateUtils.FORMAT_SHORT).toString()
            mContentTv.text = result.content
            mEvaluateStarView.setCheckStarCount(result.starCount)
            mUserIconIv.loadUrl(result.imgurl)

            result.imgurls?.let {
                mImageRv.layoutManager = GridLayoutManager(this, 3)
                mEvaluateImageAdapter = EvaluateImageAdapter(this)
                mImageRv.adapter = mEvaluateImageAdapter
                val list = result.imgurls.split(",").toMutableList()
                mEvaluateImageAdapter.setData(list)
            }

        }
    }

    /**
     * 最新体验报告
     */
    private fun loadReportData() {
        var map = mutableMapOf<String, String>()
        map.put("pid", mGoodsId.toString())
        mBasePresenter.getReportNew(map)
    }

    /**
     * 获取最新体验报告成功
     */
    override fun onGetReportNewSuccess(result: Report) {
        result?.let {
            mPhoneReportTv.text = StringUtils.setMobileStar(result.nickname)
            mDateReportTv.text = DateUtils.parseDate(result.createTime, DateUtils.FORMAT_SHORT).toString()
            mContentReportTv.text = result.content
            mEvaluateReportStarView.setCheckStarCount(result.starCount)
            mUserIconReportIv.loadUrl(result.imgurl)

            result.imgurls?.let {
                mImageReportRv.layoutManager = GridLayoutManager(this, 3)
                mEvaluateImageAdapter = EvaluateImageAdapter(this)
                mImageReportRv.adapter = mEvaluateImageAdapter
                val list = result.imgurls.split(",").toMutableList()
                mEvaluateImageAdapter.setData(list)
            }

        }
    }

    /**
     * 加入购物车成功
     */
    override fun onAddCartSuccess(result: Goods) {
        result?.let {
            ToastUtils.INSTANCE.showToast(this, getString(R.string.add_cart_success))
            AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE) + 1)
            loadCartSize()
        }
    }

    /**
     * 收藏商品成功
     */
    override fun onCollectGoodSuccess(result: GoodsCollect) {
        mCollectionTv.setText(getString(R.string.collect_already))
    }

    /**
     *  取消收藏商品成功
     */
    override fun onDataIsNull() {
        mCollectionTv.setText(getString(R.string.collect_goods))
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}