package com.yizhipin.base.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.support.v4.content.ContextCompat
import com.yizhipin.base.R


/**
 * Created by XiLei on 2018/10/17
 * Dialog基类
 */
class BaseAlertDialog(private val mContext: Context) {
    private val mDialog: AlertDialog? = null
    private var mBuilder: AlertDialog.Builder? = null
    private var mOkClickInterface: OkClickInterface? = null

    init {
        initDialog()
    }

    private fun initDialog() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mBuilder = AlertDialog.Builder(mContext)
        } else {
            mBuilder = AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert)
        }

        mBuilder!!.setPositiveButton(mContext.getString(R.string.confirm)) { dialog, which ->
            mOkClickInterface!!.okClickListener()
            dialog.dismiss()
        }
        mBuilder!!.setNegativeButton(mContext.getString(R.string.cancel)) { dialog, which -> dialog.dismiss() }
    }

    fun setTitle(str: String) {
        mBuilder!!.setTitle(str)
    }

    fun setMessage(str: String) {
        mBuilder!!.setMessage(str)
    }

    fun show() {

        //设置button颜色必须放在show方法之后，否则getButton为null
        val alertDialog = mBuilder!!.create()
        alertDialog.show()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(mContext, R.color.yMain))
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(mContext, R.color.yMain))
        }
    }

    interface OkClickInterface {
        fun okClickListener()
    }

    fun setOkClickInterface(okClickInterface: OkClickInterface) {
        mOkClickInterface = okClickInterface
    }
}
