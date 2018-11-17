package com.yizhipin.goods.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.yizhipin.goods.R
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.ui.activity.SearchGoodsActivity
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.layout_sku_view.view.*
import org.jetbrains.anko.startActivity

/*
    单个SKU
 */
class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.layout_sku_view, this)
    }

    /*
        动态设置SKU数据
     */
    fun setSkuData(goodsSku: MutableList<String>) {

        //FlowLayout设置数据
        mSkuContentView.adapter = object : TagAdapter<String>(goodsSku) {
            override fun getView(parent: com.zhy.view.flowlayout.FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_sku_item, parent, false) as TextView
                view.text = t
                return view
            }
        }

        mSkuContentView.setOnTagClickListener { _, position, _ ->

            if (mSkuContentView.selectedList.size > 0) {
                context.startActivity<SearchGoodsActivity>(GoodsConstant.KEY_GOODS_KEYWORD to goodsSku.get(position))
            } else {
                mSkuContentView.adapter.setSelectedList(setOf(position))
            }
            true
        }
    }

}
