package com.yizhipin.base.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.base.utils.NetWorkUtils
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var mLifecycleProvider: LifecycleProvider<*>
    @Inject
    lateinit var context: Context

    fun checkNetWork(): Boolean {
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}