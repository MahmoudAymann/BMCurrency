package com.bm.currency.core.di

import com.bm.currency.BuildConfig
import com.bm.currency.core.network.CurrencyApiService
import com.bm.currency.features.fragment.convertcurrency.data.CurrencyConverterRepoImpl
import com.bm.currency.features.fragment.convertcurrency.domain.CurrencyConverterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


/**
 * Created by Mahmoud Ayman on 14/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideAdmin96Retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FixerBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        with(okHttpClientBuilder) {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService =
        retrofit.create(CurrencyApiService::class.java)

    @Singleton
    @Provides
    fun provideCurrencyConverterRepo(currencyApiService: CurrencyApiService): CurrencyConverterRepo =
        CurrencyConverterRepoImpl(currencyApiService)


}