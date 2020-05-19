package ru.ventra.github.jobs.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.ventra.github.jobs.ui.position.PositionsViewModel

val viewModelModule = module {

    viewModel { PositionsViewModel() }
}
