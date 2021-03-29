package com.acronym.app.network

import okhttp3.Interceptor
import okhttp3.Response

object CookiesInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        return originalResponse

    }
}