package com.netanel.hometest.domain.mappers

interface IMapper<in T, out D> {
    fun map(response: T): D
}
