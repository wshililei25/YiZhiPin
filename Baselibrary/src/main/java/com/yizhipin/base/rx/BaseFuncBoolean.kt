package com.yizhipin.base.rx

import com.yizhipin.base.common.BaseResultCode
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by ${XiLei} on 2018/8/5.
 * 只返回boolean时使用
 */
class BaseFuncBoolean<T> : Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(t: BaseResp<T>): Observable<Boolean> {
        if (!t.code.equals(BaseResultCode.SUCCESS)) {
            return Observable.error(BaseException(t.code, t.msg))
        }
        return Observable.just(true)
    }
}