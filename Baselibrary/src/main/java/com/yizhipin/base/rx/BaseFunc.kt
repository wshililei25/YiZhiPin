package com.yizhipin.base.rx

import com.yizhipin.base.common.BaseResultCode
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by ${XiLei} on 2018/8/5.
 * 无分页数据时使用
 */
class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (!t.code.equals(BaseResultCode.SUCCESS)) {
            return Observable.error(BaseException(t.code, t.msg))
        }

        if (t.data == null) {
            return Observable.error(DataNullException())
        }
        return Observable.just(t.data)
    }
}