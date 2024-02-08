package com.netanel.hometest.domain.model

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */

/**
 * Sealed class representing the result of network operations.
 */
open class Result<out T> {
    // Represents a successful result containing data of type T
    data class Success<out T>(val data: T) : Result<T>()

    // Represents an error result containing optional error details
    data class Error(val error: DefaultRestEntity? = null) : Result<Nothing>()
}
