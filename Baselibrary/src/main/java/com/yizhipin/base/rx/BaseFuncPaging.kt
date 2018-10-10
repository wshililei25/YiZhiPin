package com.yizhipin.base.rx

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by ${XiLei} on 2018/8/5.
 * 有分页数据时使用
 */
class BaseFuncPaging<T> : Function<T, Observable<T>> {
    override fun apply(t: T): Observable<T> {
       /* if (!t.code.equals(BaseResultCode.SUCCESS)) {
            return Observable.error(BaseException(t.code, t.msg))
        }

        if (t.data == null) {
            return Observable.error(DataNullException())
        }*/
        return Observable.just(t)
    }
}