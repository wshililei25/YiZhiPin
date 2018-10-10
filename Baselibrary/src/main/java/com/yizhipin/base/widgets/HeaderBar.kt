package com.yizhipin.base.widgets

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.yizhipin.base.R
import com.yizhipin.base.ext.onClick
import kotlinx.android.synthetic.main.header_bar.view.*

/**
 * Created by ${XiLei} on 2018/8/5.
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mIsShowBack: Boolean = true
    private var mTitleText: String? = null
    private var mRightText: String? = null
    private var mBgColor: Int
    private var mTitleTextColor: Int
    private var mRightTextColor: Int
    private var mBackImage: Int

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        mIsShowBack = typeArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        mTitleText = typeArray.getString(R.styleable.HeaderBar_titleText)
        mRightText = typeArray.getString(R.styleable.HeaderBar_rightText)
        mBgColor = typeArray.getColor(R.styleable.HeaderBar_bg, ContextCompat.getColor(context, R.color.yMain))
        mTitleTextColor = typeArray.getColor(R.styleable.HeaderBar_titleTextColor, ContextCompat.getColor(context, R.color.yWhite))
        mRightTextColor = typeArray.getColor(R.styleable.HeaderBar_rightTextColor, ContextCompat.getColor(context, R.color.yWhite))
        mBackImage = typeArray.getResourceId(R.styleable.HeaderBar_backImage, R.drawable.leftarrow3)
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.header_bar, this)
        mBackIv.visibility = if (mIsShowBack) View.VISIBLE else View.GONE
        mTitleText?.let {
            mTitleTv.text = it
        }
        mRightText?.let {
            mRightTv.text = it
        }
        mBgColor?.let {
            mHeadView.setBackgroundColor(mBgColor)
        }
        mTitleTextColor?.let {
            mTitleTv.setTextColor(mTitleTextColor)
        }
        mRightTextColor?.let {
            mRightTv.setTextColor(mRightTextColor)
        }
        mBackIv?.let {
            mBackIv.setImageDrawable(context.resources.getDrawable(mBackImage))
        }

        mBackIv.onClick {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }

    fun getTiTleTv(): TextView {
        return mTitleTv
    }


    fun getBackIv(): ImageView {
        return mBackIv
    }

    fun getRightTv(): TextView {
        return mRightTv
    }

    fun getRightText(): String {
        return mRightTv.text.toString()
    }

    fun getBgView(): View {
        return mHeadView
    }
}