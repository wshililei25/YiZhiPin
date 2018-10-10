package com.yizhipin.base.ui.fragment

import android.os.Bundle
import com.yizhipin.base.common.BaseApplication
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.base.injection.component.DaggerActivityComponent
import com.yizhipin.base.injection.moudule.ActivityModule
import com.yizhipin.base.injection.moudule.LifecycleProviderModule
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/5/28.
 */
open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mBasePresenter: T

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((act.application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(act))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun onError(mes: String) {
        toast(mes)
    }
}