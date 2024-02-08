package com.netanel.hometest.domain.mappers

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */

/**
 * Interface for mapping network responses to domain models.
 * @param T The type of the network response.
 * @param D The type of the domain model to be mapped to.
 */
interface IMapper<in T, out D> {
    /**
     * Maps the network response to a domain model.
     * @param response The network response to be mapped.
     * @return The mapped domain model.
     */
    fun map(response: T): D
}
