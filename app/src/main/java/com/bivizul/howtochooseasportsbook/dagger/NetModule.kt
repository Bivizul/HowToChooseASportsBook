package com.bivizul.howtochooseasportsbook.dagger

import com.bivizul.howtochooseasportsbook.data.Constant.BASE_URL
import com.bivizul.howtochooseasportsbook.data.NetService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    fun provideUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideNet(baseUrl: String): NetService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetService::class.java)

}