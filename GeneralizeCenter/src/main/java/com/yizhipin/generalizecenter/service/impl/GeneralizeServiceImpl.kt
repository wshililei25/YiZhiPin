package com.yizhipin.generalizecenter.service.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.generalizecenter.service.GeneralizeService
import com.yizhipin.goods.data.repository.GeneralizeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class GeneralizeServiceImpl @Inject constructor() : GeneralizeService {

    @Inject
    lateinit var mRepository: GeneralizeRepository

    override fun getGenBiddingList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<GeneralizeCollect>>> {

        return mRepository.getGenBiddingList(map).convertPaging()
    }
    override fun getGenBiddingDetails(map: MutableMap<String, String>): Observable<GeneralizeCollect> {

        return mRepository.getGenBiddingDetails(map).convert()
    }
    override fun payPersonage(map: MutableMap<String, String>): Observable<GeneralizeCollect> {

        return mRepository.payPersonage(map).convert()
    }

}