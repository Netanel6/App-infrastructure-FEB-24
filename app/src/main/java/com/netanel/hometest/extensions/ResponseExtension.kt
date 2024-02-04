package com.netanel.hometest.extensions

import com.google.gson.GsonBuilder
import com.netanel.hometest.utils.Logger
import retrofit2.Response

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */

private const val TAG = "~Network Response~"

fun Response<*>.print() {
    Logger.info(TAG, if (!this.code().isError) successMessage else errorMessage)
}

@Suppress("IMPLICIT_CAST_TO_ANY") // Added this suppression for headers section
private val Response<*>.successMessage: String
    get() {
        val url = this.raw().request.url
        val method = this.raw().request.method
        val code = this.code()
        val headers =
            if (this.raw().request.headers.size != 0) this.raw().request.headers else "No headers included"
        val response = this.body()?.jsonPrinted
        return """
✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅
ℹ️ Success from URL: $url

ℹ️ Method: $method

ℹ️ Status Code: $code

ℹ️ Headers: $headers

ℹ️ Response: $response
✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅
"""
    }

@Suppress("IMPLICIT_CAST_TO_ANY") // Added this suppression for headers section
private val Response<*>.errorMessage: String
    get() {
        val url = this.raw().request.url
        val method = this.raw().request.method
        val code = this.code()
        val headers =
            if (this.raw().request.headers.size != 0) this.raw().request.headers else "No headers included"
        val response = this.body()?.jsonPrinted ?: this.errorBody()?.string()?.jsonPrinted
        return """
💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩
🚨️ Error from URL: $url

🚨️ Method: $method

🚨️ Status Code: $code

🚨️ Headers: $headers

🚨️ Response: $response
💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩💩
"""
    }

private val Int.isError: Boolean
    get() = this >= 400 || this == 203 || this == 207

private val Any.jsonPrinted: String
    get() {
        return try {
            GsonBuilder().setPrettyPrinting().create().toJson(this)
        } catch (e: Exception) {
            "Failed to parse error from response - ${e.message}"
        }
    }
