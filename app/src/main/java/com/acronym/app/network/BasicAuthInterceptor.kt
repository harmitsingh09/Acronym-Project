package com.acronym.app.network

import android.util.Log
import com.acronym.app.SnippetApplication
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor constructor(
    private val user: String,
    private val password: String
) : Interceptor {

    private val credentials = Credentials.basic(user, password)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        if (SnippetApplication.getInstance()?.getApiRemainingLimit() === 0) {
            Log.i("Interceptor", "wait for 1 sec ")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                Log.i("Interceptor", e.message!!)
            }
        }
        Log.i("Interceptor", "api call " + request.url())
        return chain.proceed(authenticatedRequest)
    }
}