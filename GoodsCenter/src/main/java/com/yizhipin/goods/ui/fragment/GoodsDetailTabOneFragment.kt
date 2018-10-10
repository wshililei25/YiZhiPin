package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.event.AddCartEvent
import com.yizhipin.base.event.SkuChangedEvent
import com.yizhipin.base.event.UpdateCartSizeEvent
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.widgets.BannerImageLoader
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.injection.component.DaggerGoodsComponent
import com.yizhipin.goods.injection.module.GoodsModule
import com.yizhipin.goods.presenter.GoodsDetailPresenter
import com.yizhipin.goods.presenter.view.GoodsDetailView
import com.yizhipin.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView, View.OnClickListener {
    override fun onGetReportNewSuccess(result: Report) {

    }

    override fun onGetEvaluateNewSuccess(result: Evaluate) {
    }

    private lateinit var mGoodsSkuPopView: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation
    private var mCurGoods: Goods? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBannerView()
        initSkuPop()
        loadData()
        initObserve()
        initAnim()
    }

    private fun initView() {
        mSkuView.onClick(this)
    }

    private fun initBannerView() {
        //设置图片加载器
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        mGoodsDetailBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mGoodsDetailBanner.isAutoPlay(true);
        //设置轮播时间
        mGoodsDetailBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    private fun initSkuPop() {
        mGoodsSkuPopView = GoodsSkuPopView(activity!!)
        mGoodsSkuPopView.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
//        mBasePresenter.getGoodsDetail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun onGetGoodsDetailSuccess(result: Goods) {

       /* mCurGoods = result
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        //banner设置方法全部调用完毕时最后调用
        mGoodsDetailBanner.start();

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)*/
    }

    private fun loadPopData(result: Goods) {
      /*  mGoodsSkuPopView.setGoodsIcon(result.goodsDefaultIcon)
        mGoodsSkuPopView.setGoodsCode(result.goodsCode)
        mGoodsSkuPopView.setGoodsPrice(result.goodsDefaultPrice)
        mGoodsSkuPopView.setSkuData(result.goodsSku)*/
    }

    override fun onAddCartSuccess(result: Int) {
        toast("加入购物车成功")
        Bus.send(UpdateCartSizeEvent())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSkuView -> {
                mGoodsSkuPopView.showAtLocation((activity as BaseActivity).contentView, Gravity.BOTTOM, 0, 0)
                (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
            }
        }
    }

    /*
    初始化缩放动画
 */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /**
     * 监听SKU变化及加入购物车事件
     */
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mGoodsSkuPopView.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mGoodsSkuPopView.getSelectCount() + "件"
                }
                .registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
    加入购物车
 */
    private fun addCart() {
        mCurGoods?.let {
           /* mBasePresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mGoodsSkuPopView.getSelectCount(),
                    mGoodsSkuPopView.getSelectSku()
            )*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



