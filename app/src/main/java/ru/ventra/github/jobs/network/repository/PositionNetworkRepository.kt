package ru.ventra.github.jobs.network.repository

import ru.ventra.github.jobs.network.api.JobsApiService
import ru.ventra.github.jobs.persistence.entity.Position

class PositionNetworkRepository(private val api: JobsApiService) {

//    suspend fun getPositionById(id: String): Position? = api.getById(id)

    suspend fun getAllPositions(): List<Position> = api.getPositions()

    suspend fun searchPositions(search: String): List<Position> = api.searchPositions(search)
}
