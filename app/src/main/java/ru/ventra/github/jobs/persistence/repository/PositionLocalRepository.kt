package ru.ventra.github.jobs.persistence.repository

import ru.ventra.github.jobs.persistence.dao.PositionDao
import ru.ventra.github.jobs.persistence.entity.Position

class PositionLocalRepository(private val dao: PositionDao) {

    suspend fun getPositionById(id: String): Position? = dao.findById(id)

    suspend fun getAllPositions(): List<Position> = dao.findAll()

    suspend fun savePosition(position: Position) = dao.saveOne(position)

    suspend fun savePositions(positions: List<Position>) = dao.saveAll(positions)

    suspend fun clearPositions() = dao.deleteAll()
}
