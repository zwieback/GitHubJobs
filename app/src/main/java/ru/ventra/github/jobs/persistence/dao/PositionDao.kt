package ru.ventra.github.jobs.persistence.dao

import androidx.room.*
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.persistence.entity.PositionUpdate
import ru.ventra.github.jobs.persistence.entity.toUpdate

@Dao
interface PositionDao {

    @Query("SELECT * FROM t_position WHERE id = :id")
    suspend fun findById(id: String): Position?

    @Query("SELECT * FROM t_position ORDER BY id")
    suspend fun findAll(): List<Position>

    @Query("SELECT * FROM t_position WHERE title LIKE '%' || :search || '%' OR description LIKE '%' || :search || '%' ORDER BY id")
    suspend fun searchByTitleOrDescription(search: String): List<Position>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(position: Position): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(positions: List<Position>): List<Long>

    @Update
    suspend fun updateOne(position: Position)

    @Update
    suspend fun updateAll(positions: List<Position>)

    /**
     * [Get idea from here](https://stackoverflow.com/a/59834309)
     */
    @Update(entity = Position::class)
    suspend fun updateOneWithoutFavoriteLoss(position: PositionUpdate)

    /**
     * [Get idea from here](https://stackoverflow.com/a/59834309)
     */
    @Update(entity = Position::class)
    suspend fun updateAllWithoutFavoriteLoss(positions: List<PositionUpdate>)

    /**
     * Works like an [UPSERT](https://www.sqlite.org/draft/lang_UPSERT.html)
     *
     * [Copied from SO](https://stackoverflow.com/a/50736568)
     */
    @Transaction
    suspend fun upsertOne(position: Position) {
        val id = insertOne(position)
        if (id == -1L) {
            updateOne(position)
        }
    }

    /**
     * Works like an [UPSERT](https://www.sqlite.org/draft/lang_UPSERT.html)
     *
     * [Copied from SO](https://stackoverflow.com/a/50736568)
     */
    @Transaction
    suspend fun upsertAll(positions: List<Position>) {
        val insertResult = insertAll(positions)
        val updateList = insertResult.mapIndexedNotNull { index, result ->
            if (result == -1L) {
                positions[index]
            } else {
                null
            }
        }
        if (updateList.isNotEmpty()) {
            updateAll(updateList)
        }
    }

    @Transaction
    suspend fun saveOne(position: Position) {
        val id = insertOne(position)
        if (id == -1L) {
            updateOneWithoutFavoriteLoss(position.toUpdate())
        }
    }

    @Transaction
    suspend fun saveAll(positions: List<Position>) {
        val insertResult = insertAll(positions)
        val updateList = insertResult.mapIndexedNotNull { index, result ->
            if (result == -1L) {
                positions[index].toUpdate()
            } else {
                null
            }
        }
        if (updateList.isNotEmpty()) {
            updateAllWithoutFavoriteLoss(updateList)
        }
    }

    @Query("DELETE FROM t_position")
    suspend fun deleteAll()
}
