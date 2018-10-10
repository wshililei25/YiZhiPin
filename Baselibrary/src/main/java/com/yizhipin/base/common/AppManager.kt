package com.yizhipin.base.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by ${XiLei} on 2018/8/5.
 */
class AppManager private constructor() {

    private val mActivityStack: Stack<Activity> = Stack()

    /**
     * 单例
     */
    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /**
     * 入栈
     */
    fun addActivity(activity: Activity) {
        mActivityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        mActivityStack.remove(activity)
    }

    /**
     * 栈顶的acitivity
     */
    fun currentActivity(activity: Activity) {
        mActivityStack.lastElement()
    }

    /**
     * 清理栈
     */
    fun finishAllActivity() {
        for (activity in mActivityStack) {
            activity.finish()
        }
        mActivityStack.clear()
    }

    /**
     * 退出App
     */
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}