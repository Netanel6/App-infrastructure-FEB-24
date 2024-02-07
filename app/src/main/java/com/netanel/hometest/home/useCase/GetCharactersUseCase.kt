package com.netanel.hometest.home.useCase

import com.netanel.hometest.domain.model.Result
import com.netanel.hometest.home.model.Characters
import com.netanel.hometest.home.repository.HomeRepository
import javax.inject.Inject

class GetCharactersUseCase
    @Inject
    constructor(private val repository: HomeRepository) {
        suspend operator fun invoke(): Result<Characters?> {
            return repository.getCharacters()
        }
    }
