package com.yizhipin.base.rx

import com.yizhipin.base.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
open  class BaseSubscriber<T>(val baseView: BaseView) : Observer<T> {
    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if(e is BaseException){
            baseView.onError(e.msg)
        } else if (e is DataNullException){
            baseView.onDataIsNull()
        }
    }

    override fun onComplete() {
        baseView.hideLoading()
    }

}