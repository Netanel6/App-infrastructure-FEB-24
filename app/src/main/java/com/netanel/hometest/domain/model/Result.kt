package com.netanel.hometest.domain.model

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
open class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val error: DefaultRestEntity? = null) : Result<Nothing>()
}
