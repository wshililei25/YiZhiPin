package com.yizhipin.base.data.net

import com.yizhipin.base.common.BaseConstant
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class RetrofitFactoryPost @Inject constructor(map: MutableMap<String, String>) {

    private val mRetrofit: Retrofit
    private val mInterceptor: Interceptor

    init {

        var formBody = FormBody.Builder()
        for (m in map) {
            formBody.add(m.key, m.value)
        }

        mInterceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("charset", "utf-8")
                    .post(formBody.build())
                    .build()
            chain.proceed(request)
        }

        mRetrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVICE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}