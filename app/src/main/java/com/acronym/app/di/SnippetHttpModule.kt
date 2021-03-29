package com.acronym.app.di

import com.acronym.app.BuildConfig
import com.acronym.app.constant.ApiConstant.OK_HTTP_CLIENT_TIME_OUT
import com.acronym.app.network.CookiesInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SnippetHttpModule {
    @SnippetApiModule
    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @SnippetApiModule
    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @SnippetApiModule
    @Singleton
    @Provides
    fun providesCookiesInterceptor(): CookiesInterceptor {
        return CookiesInterceptor
    }

    @SnippetApiModule
    @Singleton
    @Provides
    fun provideOkHttpClient( @SnippetApiModule loggingInterceptor: HttpLoggingInterceptor ,@SnippetApiModule cookiesInterceptor: CookiesInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @SnippetApiModule
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @SnippetApiModule
    @Singleton
    @Provides
    fun provideRetrofitClient(@SnippetApiModule okHttpClient: OkHttpClient, @SnippetApiModule baseUrl: String, @SnippetApiModule converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

}