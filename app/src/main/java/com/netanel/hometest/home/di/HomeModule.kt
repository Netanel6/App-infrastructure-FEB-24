package com.netanel.hometest.home.di

import com.netanel.hometest.domain.mappers.ResponseToDataMapper
import com.netanel.hometest.home.domain.HomeApiService
import com.netanel.hometest.home.model.Characters
import com.netanel.hometest.home.repository.HomeRepository
import com.netanel.hometest.home.repository.HomeRepositoryImpl
import com.netanel.hometest.home.useCase.GetCharactersUseCase
import com.netanel.hometest.home.view.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun provideResponseMapper(): ResponseToDataMapper<Characters> {
        return ResponseToDataMapper()
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        apiService: HomeApiService,
        responseMapper: ResponseToDataMapper<Characters>,
    ): HomeRepository {
        return HomeRepositoryImpl(apiService, responseMapper)
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    class ViewModelModule {
        @Provides
        fun provideGetCharactersUseCase(repository: HomeRepository): GetCharactersUseCase {
            return GetCharactersUseCase(repository)
        }

        @Provides
        fun provideMyViewModel(getCharactersUseCase: GetCharactersUseCase): HomeViewModel {
            return HomeViewModel(getCharactersUseCase)
        }
    }
}
