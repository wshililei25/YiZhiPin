package com.yizhipin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.event.GoodsDetailImageEvent
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.goods.R
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * Created by ${XiLei} on 2018/8/23.
 */
class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribe { t: GoodsDetailImageEvent ->
                    run {
                        mGoodsDetailOneIv.loadUrl(t.imgOne)
                        mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                    }
                }
                .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



