package com.netanel.hometest.domain

import com.netanel.hometest.home.domain.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance() = RetrofitInstance

    @Singleton
    @Provides
    fun provideNetworkManager(retrofitInstance: RetrofitInstance): ApiService {
        return retrofitInstance.create(ApiService::class.java)
    }
}
