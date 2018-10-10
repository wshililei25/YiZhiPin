package com.yizhipin.goods.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.presenter.view.GoodsListView
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl

    fun getGoodsList(categoryId: Int, pageNo: Int) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mGoodsServiceImpl.getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsListSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    /*
    根据关键字 搜索商品
 */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mGoodsServiceImpl.getGoodsListByKeyword(keyword,pageNo).execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListSuccess(t)
            }
        }, mLifecycleProvider)

    }
}