package com.netanel.hometest.home.useCase

import com.netanel.hometest.domain.model.Result
import com.netanel.hometest.home.model.Characters
import com.netanel.hometest.home.repository.HomeRepository
import javax.inject.Inject

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
class GetCharactersUseCase
    @Inject
    constructor(private val repository: HomeRepository) {
        suspend operator fun invoke(): Result<Characters?> {
            return repository.getCharacters()
        }
    }
