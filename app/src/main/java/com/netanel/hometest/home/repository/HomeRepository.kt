package com.netanel.hometest.home.repository

import com.netanel.hometest.domain.mappers.ResponseToDataMapper
import com.netanel.hometest.domain.model.Result
import com.netanel.hometest.home.domain.HomeApiService
import com.netanel.hometest.home.model.Characters
import javax.inject.Inject

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */
interface HomeRepository {
    suspend fun getCharacters(): Result<Characters?>
}

class HomeRepositoryImpl
    @Inject
    constructor(
        private val networkManager: HomeApiService,
        private val responseMapper: ResponseToDataMapper<Characters>,
    ) : HomeRepository {
        override suspend fun getCharacters(): Result<Characters?> {
            val response = networkManager.getCharacters()
            return responseMapper.map(response)
        }
    }
