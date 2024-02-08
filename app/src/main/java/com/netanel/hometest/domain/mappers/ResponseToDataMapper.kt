package com.netanel.hometest.domain.mappers

import com.netanel.hometest.domain.model.DefaultRestEntity
import com.netanel.hometest.domain.model.Result
import retrofit2.Response

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */

/**
 * Mapper class for converting Retrofit Response objects to Result objects,
 * handling success and error cases.
 * @param T The type of data expected in the Response.
 */
class ResponseToDataMapper<T> : IMapper<Response<T>, Result<T?>> {
    /**
     * Maps Retrofit Response object to a Result object.
     * @param response The Retrofit Response object to be mapped.
     * @return Result object representing the mapped response.
     */
    override fun map(response: Response<T>): Result<T?> {
        return try {
            if (response.isSuccessful) {
                // If the response is successful, return a Success result with the response body
                Result.Success(response.body())
            } else {
                // If the response is unsuccessful, return an Error result with default error entity
                Result.Error(DefaultRestEntity())
            }
        } catch (e: Exception) {
            // If an exception occurs during mapping, return an Error result with error details
            Result.Error(
                DefaultRestEntity(
                    error = e.localizedMessage,
                    stack = e.stackTraceToString(),
                    result = null,
                ),
            )
        }
    }
}
