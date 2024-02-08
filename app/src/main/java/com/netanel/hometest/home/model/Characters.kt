package com.netanel.hometest.home.model

import com.netanel.hometest.domain.model.DefaultRestEntity
import com.netanel.hometest.home.model.character.Character

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
data class Characters(
    val info: Info,
    val results: List<Character>,
) : DefaultRestEntity()
