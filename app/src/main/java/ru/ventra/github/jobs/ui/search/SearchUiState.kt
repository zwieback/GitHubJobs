package ru.ventra.github.jobs.ui.search

import ru.ventra.github.jobs.persistence.entity.Position

sealed class SearchUiState {
    object Loading : SearchUiState()
    data class Success(val positions: List<Position>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
