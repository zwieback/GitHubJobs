package ru.ventra.github.jobs.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.ventra.github.jobs.ui.position.PositionsViewModel
import ru.ventra.github.jobs.ui.position.detail.PositionDetailViewModel
import ru.ventra.github.jobs.ui.search.SearchViewModel

val viewModelModule = module {

    viewModel { PositionsViewModel() }

    viewModel { PositionDetailViewModel() }

    viewModel { SearchViewModel() }
}
