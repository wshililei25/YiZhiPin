package com.yizhipin.base.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.yizhipin.base.R

/**
 *  Created by ${XiLei} on 2018/9/2.
 */
enum class ToastUtils {
    INSTANCE;
    // 实现单例
    private var mToast: Toast? = null
    private var mTvToast: TextView? = null

    fun showToast(ctx: Context, content: String) {
        if (mToast == null) {
            mToast = Toast(ctx)
            mToast!!.setGravity(Gravity.BOTTOM, 0, 20)//设置toast显示的位置，这是居中
            mToast!!.duration = Toast.LENGTH_SHORT//设置toast显示的时长
            val _root = LayoutInflater.from(ctx).inflate(R.layout.toast_custom_common, null)//自定义样式，自定义布局文件
            mTvToast = _root.findViewById<View>(R.id.tvCustomToast) as TextView
            mToast!!.view = _root//设置自定义的view
        }
        mTvToast!!.text = content//设置文本
        mToast!!.show()//展示toast
    }

    fun showToast(ctx: Context, stringId: Int) {
        showToast(ctx, ctx.getString(stringId))
    }

    fun cancelToast() {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
            mTvToast = null
        }
    }
}
