package com.netanel.hometest.home.model

import com.netanel.hometest.domain.model.DefaultRestEntity
import com.netanel.hometest.home.model.character.Character

data class Characters(
    val info: Info,
    val results: List<Character>,
) : DefaultRestEntity()
