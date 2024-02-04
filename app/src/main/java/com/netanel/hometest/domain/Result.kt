package com.netanel.hometest.domain

open class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val error: DefaultRestEntity? = null) : Result<Nothing>()
}
