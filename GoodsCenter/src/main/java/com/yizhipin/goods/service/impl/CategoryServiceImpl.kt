package com.yizhipin.goods.service.impl

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.goods.data.repository.CategoryRepository
import com.yizhipin.goods.data.response.Category
import com.yizhipin.goods.data.response.CategorySecond
import com.yizhipin.goods.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var mRepository: CategoryRepository

    override fun getCategoryAll(): Observable<MutableList<Category>?> {

        return mRepository.getCategoryAll().convert()
    }

    override fun getCategorySecond(map: MutableMap<String, String>): Observable<MutableList<CategorySecond>?> {

        return mRepository.getCategorySecond(map).convert()
    }

    override fun getGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>> {

        return mRepository.getGoodsList(map).convertPaging()
    }
    override fun getShopGoodsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Goods>?>> {

        return mRepository.getShopGoodsList(map).convertPaging()
    }


}