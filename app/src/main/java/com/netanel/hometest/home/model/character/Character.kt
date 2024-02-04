package com.netanel.hometest.home.model.character

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
)
