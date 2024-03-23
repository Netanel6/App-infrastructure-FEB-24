package com.netanel.hometest.domain

import com.netanel.hometest.domain.interceptors.LoggingInterceptor
import com.netanel.hometest.domain.model.NetworkUtils
import com.netanel.hometest.utils.Logger
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Netanel Amar on 01/01/2024.
 * NetanelCA2@gmail.com
 */

/**
 * Singleton object responsible for managing Retrofit instances and creating API services.
 */
object RetrofitInstance {
    // Tag for logging purposes
    val TAG = this::class.simpleName.toString()

    // OkHttpClient instance for handling HTTP requests
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

    // Retrofit instance configured with base URL, OkHttpClient, and GsonConverterFactory
    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkUtils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Function to dynamically create and return an instance of a specified API service interface.
     * @return Instance of the specified API service interface.
     */
    inline fun <reified T> create(): T {
        // Log message indicating the creation of API service instance
        Logger.info(TAG, "create: true")
        // Return the API service instance created by Retrofit
        return retrofit.create(T::class.java)
    }
}
