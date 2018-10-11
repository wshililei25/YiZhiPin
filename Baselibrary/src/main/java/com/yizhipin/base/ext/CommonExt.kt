package com.yizhipin.base.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kennyc.view.MultiStateView
import com.trello.rxlifecycle2.LifecycleProvider
import com.yizhipin.base.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.rx.BaseFunc
import com.yizhipin.base.rx.BaseFuncBoolean
import com.yizhipin.base.rx.BaseFuncPaging
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.base.utils.GlideUtils
import com.yizhipin.base.widgets.DefaultTextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

/**
 * Created by ${XiLei} on 2018/7/26.
 */

fun <T> Observable<T>.execute(baseSubscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(baseSubscriber)
}

/**
 * 无分页时使用
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

/**
 * 有分页时使用
 */
fun <T> Observable<T>.convertPaging(): Observable<T> {
    return this.flatMap(BaseFuncPaging())
}

/**
 * 只返回boolean时使用
 */
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

/**
 * 点击事件扩展方法
 */
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

/**
 * 点击事件扩展方法
 */
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

/**
 * 扩展Button可用性
 */
fun Button.enable(editText: EditText, method: () -> Boolean) {
    val btn = this
    editText.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

/**
 * ImageView加载网络图片 扩展
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, BaseConstant.IMAGE_SERVICE_ADDRESS + url, this)
}

/**
 * 多状态视图开始加载
 */
fun MultiStateView.startLoading() {
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackGround = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackGround as AnimationDrawable).start()
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}