package ru.ventra.github.jobs.persistence.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration2To3 : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE INDEX IF NOT EXISTS idx_title_description ON t_position (title, description)")
    }
}
