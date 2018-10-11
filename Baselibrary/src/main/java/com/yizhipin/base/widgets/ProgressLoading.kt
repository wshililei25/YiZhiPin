package com.yizhipin.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.yizhipin.base.R
import org.jetbrains.anko.find

/**
 * Created by ${XiLei} on 2018/8/12.
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private lateinit var mDialog: ProgressLoading
        private var mAnimationListener: AnimationDrawable? = null

        fun create(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp

            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            mAnimationListener = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoading() {
//        super.show()
        mAnimationListener?.start()
    }

    fun hideLoading() {
        super.dismiss()
        mAnimationListener?.stop()
    }
}