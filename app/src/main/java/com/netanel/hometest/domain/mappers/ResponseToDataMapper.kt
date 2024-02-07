package com.netanel.hometest.domain.mappers

import com.netanel.hometest.domain.model.DefaultRestEntity
import com.netanel.hometest.domain.model.Result
import retrofit2.Response

/**
 * Created by netanelamar on 07/02/2024.
 * NetanelCA2@gmail.com
 */
class ResponseToDataMapper<T> : IMapper<Response<T>, Result<T?>> {
    override fun map(response: Response<T>): Result<T?> {
        return try {
            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(DefaultRestEntity())
            }
        } catch (e: Exception) {
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
