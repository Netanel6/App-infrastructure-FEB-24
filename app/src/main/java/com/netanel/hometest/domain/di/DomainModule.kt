package com.netanel.hometest.domain.di

import com.netanel.hometest.domain.RetrofitInstance
import com.netanel.hometest.home.domain.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by netanelamar on 01/01/2024.
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
    fun provideNetworkManager(retrofitInstance: RetrofitInstance): HomeApiService {
        return retrofitInstance.create()
    }
}
