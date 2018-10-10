package com.yizhipin.base.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by ${XiLei} on 2018/8/4.
 *  Activity级别 作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope