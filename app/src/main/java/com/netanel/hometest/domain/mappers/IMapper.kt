package com.netanel.hometest.domain.mappers

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
interface IMapper<in T, out D> {
    fun map(response: T): D
}
