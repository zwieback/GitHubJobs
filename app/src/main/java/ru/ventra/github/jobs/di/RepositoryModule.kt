package ru.ventra.github.jobs.di

import org.koin.dsl.module
import ru.ventra.github.jobs.network.repository.PositionNetworkRepository
import ru.ventra.github.jobs.persistence.repository.PositionLocalRepository

val localRepositoryModule = module {

    single { PositionLocalRepository(get()) }
}

val networkRepositoryModule = module {

    single { PositionNetworkRepository(get()) }
}
