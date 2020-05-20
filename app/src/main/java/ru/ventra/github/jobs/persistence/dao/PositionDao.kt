package ru.ventra.github.jobs.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ventra.github.jobs.persistence.entity.Position

@Dao
interface PositionDao {

    @Query("SELECT * FROM t_position WHERE id = :id")
    suspend fun findById(id: String): Position?

    @Query("SELECT * FROM t_position ORDER BY id")
    suspend fun findAll(): List<Position>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOne(position: Position)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(positions: List<Position>)

    @Query("DELETE FROM t_position")
    suspend fun deleteAll()
}
