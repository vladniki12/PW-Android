package com.smcorp.pw.common.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smcorp.data.network.PWService
import com.smcorp.pw.common.Constants
import com.smcorp.pw.view.PWApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

    @Provides
    fun provideApiService(gson: Gson, loggingInterceptor: HttpLoggingInterceptor): PWService {
        val timeout = Constants.Api.CONNECTION_TIMEOUT_SEC.toLong()
        val httpClient = OkHttpClient.Builder()
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor)

        val baseUrl = Constants.Api.API_SCHEME + Constants.Api.API_DOMAIN + Constants.Api.BASE_PORT

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson)) // remove line if json specification is RFC 4627
            .client(httpClient.build())
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(PWService::class.java)
    }

    @Provides
    fun provideGson(): Gson {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        return gson
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}