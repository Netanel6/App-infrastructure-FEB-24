package com.netanel.hometest.domain.interceptors

import com.netanel.hometest.utils.Logger
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)

        val headers =
            if (request.headers.size == 0) "No headers" else request.headers.joinToString(separator = ",\n") { "${it.first}: ${it.second}" }
        Logger.info(TAG, "Request: ${request.url}, Method: ${request.method}, Headers: $headers")

        Logger.info(TAG, "Response: ${response.code}")

        val successOrErrorEmoji = if (response.isSuccessful) "✅✅✅✅✅✅" else "💩💩💩💩💩💩💩"
        val responseBodyString = response.body?.string()
        Logger.info(TAG, "Response Body:\n$successOrErrorEmoji$responseBodyString$successOrErrorEmoji")

        return response.newBuilder()
            .body(responseBodyString?.toResponseBody(response.body?.contentType()))
            .build()
    }

    companion object {
        val TAG = LoggingInterceptor::class.java.simpleName
    }
}
