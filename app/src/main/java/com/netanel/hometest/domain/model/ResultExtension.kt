package com.netanel.hometest.domain.model

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
import retrofit2.Response

// Not in use
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
