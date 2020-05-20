package ru.ventra.github.jobs.persistence.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration1To2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE t_position ADD COLUMN favorite INTEGER DEFAULT 0 NOT NULL")
    }
}
