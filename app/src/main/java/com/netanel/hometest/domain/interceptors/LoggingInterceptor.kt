package com.netanel.hometest.domain.interceptors

import com.netanel.hometest.utils.Logger
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */

/**
 * Interceptor class for logging network requests and responses.
 */
class LoggingInterceptor : Interceptor {
    /**
     * Intercepts the network request and response for logging.
     * @param chain The interceptor chain.
     * @return The response after intercepting and logging.
     */
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        // Intercept the request and obtain the response
        val request: Request = chain.request()
        val response = chain.proceed(request)

        // Log request details including URL, method, and headers
        val headers =
            if (request.headers.size == 0) "No headers" else request.headers.joinToString(separator = ",\n") { "${it.first}: ${it.second}" }
        Logger.info(TAG, "Request: ${request.url}, Method: ${request.method}, Headers: $headers")

        // Log response details including status code and body
        Logger.info(TAG, "Response: ${response.code}")
        val successOrErrorEmoji = if (response.isSuccessful) "✅✅✅✅✅✅" else "💩💩💩💩💩💩💩"
        val responseBodyString = response.body?.string()
        Logger.info(TAG, "Response Body:\n$successOrErrorEmoji$responseBodyString$successOrErrorEmoji")

        // Rebuild the response to prevent consuming its body
        return response.newBuilder()
            .body(responseBodyString?.toResponseBody(response.body?.contentType()))
            .build()
    }

    companion object {
        // Tag for logging purposes
        val TAG = LoggingInterceptor::class.java.simpleName
    }
}
