package com.yizhipin.base.ui.activity

import android.os.Bundle
import com.yizhipin.base.common.BaseApplication
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.base.injection.component.DaggerActivityComponent
import com.yizhipin.base.injection.moudule.ActivityModule
import com.yizhipin.base.injection.moudule.LifecycleProviderModule
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/5/28.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mBasePresenter: T
    lateinit var mActivityComponent: ActivityComponent
    lateinit var mDialogLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
        mDialogLoading = ProgressLoading.create(this)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mDialogLoading.showLoading()
    }

    override fun hideLoading() {
        mDialogLoading.hideLoading()
    }

    override fun onError(mes: String) {
        toast(mes)
    }


}