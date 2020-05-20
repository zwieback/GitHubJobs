package ru.ventra.github.jobs.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ventra.github.jobs.persistence.dao.PositionDao
import ru.ventra.github.jobs.persistence.entity.Position

@Database(
    entities = [
        Position::class
    ],
    views = [
//        PositionView::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun positionDao(): PositionDao
}
