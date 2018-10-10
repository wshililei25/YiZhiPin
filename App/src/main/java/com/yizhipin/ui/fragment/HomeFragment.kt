package com.yizhipin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.ui.web.WebViewActivity
import com.yizhipin.base.widgets.BannerImageLoader
import com.yizhipin.common.*
import com.yizhipin.data.response.Banner
import com.yizhipin.data.response.CategoryHome
import com.yizhipin.goods.ui.activity.SearchGoodsActivity
import com.yizhipin.presenter.HomePresenter
import com.yizhipin.presenter.view.HomeView
import com.yizhipin.ui.adapter.CategoryHomeAdapter
import com.yizhipin.ui.adapter.HomeDiscountAdapter
import com.yizhipin.ui.adapter.TopicAdapter
import com.yizhipin.usercenter.injection.component.DaggerMainComponent
import com.yizhipin.usercenter.injection.module.MianModule
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class HomeFragment : BaseMvpFragment<HomePresenter>(), HomeView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
        initCategoryRv()
        initDiscount()
        initTopic()
    }

    private fun initView() {
        mSearchEt.onClick {
            startActivity<SearchGoodsActivity>()
        }
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initBanner() {
        //设置图片加载器
        mHomeBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        mHomeBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mHomeBanner.isAutoPlay(true);
        //设置轮播时间
        mHomeBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    /**
     * 公告
     */
    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利来了", "新用户立领1000元优惠券"))
    }

    private fun initCategoryRv() {
        var dataList = mutableListOf(CategoryHome(R.drawable.windmill, getString(R.string.hamlet))
                , CategoryHome(R.drawable.bed, getString(R.string.stay))
                , CategoryHome(R.drawable.mountains, getString(R.string.group_group))
                , CategoryHome(R.drawable.barchart, getString(R.string.generalize))
                , CategoryHome(R.drawable.van, getString(R.string.motor_homes)))

        mCategoryRv.layoutManager = GridLayoutManager(activity, 5)
        val categoryHomeAdapter = CategoryHomeAdapter(activity!!)
        categoryHomeAdapter.setData(dataList)
        mCategoryRv.adapter = categoryHomeAdapter
        categoryHomeAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<CategoryHome> {
            override fun onItemClick(item: CategoryHome, position: Int) {

            }
        })
    }

    /**
     * 折扣
     */
    private fun initDiscount() {
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager
        val discountAdapter = HomeDiscountAdapter(context!!)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initTopic() {
        //话题
        mTopicPager.adapter = TopicAdapter(context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        loadBannerData()
    }

    private fun loadBannerData() {
        mBasePresenter.getBanner()
    }

    override fun onGetBannerSuccess(result: MutableList<Banner>) {
        var list = arrayListOf<String>()
        for (data in result) {
            list.add(BaseConstant.IMAGE_SERVICE_ADDRESS.plus(data.bannerImgurl))
        }
        //设置图片集合
        mHomeBanner.setImages(list)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
        mHomeBanner.setOnBannerListener(object : OnBannerListener {
            override fun OnBannerClick(position: Int) {
                if (!result[position].href.isNullOrEmpty()) {
                    startActivity<WebViewActivity>(WebViewActivity.EXTRA_URL to result[position].href)
                }

            }

        })
    }
}