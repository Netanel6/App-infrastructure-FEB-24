package com.netanel.hometest.domain

/**
 * Created by netanelamar on 04/02/2024.
 * NetanelCA2@gmail.com
 */

import retrofit2.Response

fun <T> Response<T>.toData(): Result<T?> {
    return try {
        if (isSuccessful) {
            Result.Success(body())
        } else {
            Result.Error(DefaultRestEntity())
        }
    } catch (e: Exception) {
        Result.Error(
            DefaultRestEntity(
                error = e.message,
                stack = e.stackTraceToString(),
                result = null,
            ),
        )
    }
}

// Not in use
fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

// Not in use
fun <T> Result<T>.onError(action: (String) -> Unit): Result<T> {
    if (this is Result.Error) {
        action(error?.error ?: "An error occurred")
    }
    return this
}
