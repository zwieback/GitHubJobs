package ru.ventra.github.jobs.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.ventra.github.jobs.persistence.entity.Position

interface JobsApiService {

    @GET("positions.json")
    suspend fun getPositions(): List<Position>

    @GET("positions.json")
    suspend fun searchPositions(@Query("search") search: String): List<Position>
}
