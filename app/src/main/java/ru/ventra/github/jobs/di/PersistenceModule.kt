package ru.ventra.github.jobs.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.ventra.github.jobs.persistence.AppDatabase

val persistenceModule = module {

    single {
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java, "jobs.db")
//            .addMigrations(
//                Migration1To2
//            )
            .build()
    }

    single { get<AppDatabase>().positionDao() }
}
