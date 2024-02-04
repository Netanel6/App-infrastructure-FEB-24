package com.netanel.hometest.home.repository

import com.netanel.hometest.domain.Result
import com.netanel.hometest.domain.toData
import com.netanel.hometest.home.domain.ApiService
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
    constructor(private val networkManager: ApiService) : HomeRepository {
        override suspend fun getCharacters(): Result<Characters?> = networkManager.getCharacters().toData()
    }
