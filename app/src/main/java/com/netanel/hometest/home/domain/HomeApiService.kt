package com.netanel.hometest.home.domain

import com.netanel.hometest.home.model.Characters
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
interface HomeApiService {
    @GET("character")
    suspend fun getCharacters(): Response<Characters>
}
