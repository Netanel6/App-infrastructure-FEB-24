package com.netanel.hometest.domain.model

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
open class DefaultRestEntity(
    val error: String? = "An error occurred",
    val stack: String? = null,
    val result: String? = null,
) {
    override fun toString(): String {
        return "DefaultRestEntity(error=$error, stack=$stack, result=$result)"
    }
}
