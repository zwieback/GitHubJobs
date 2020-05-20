package ru.ventra.github.jobs.ui.favorite

import ru.ventra.github.jobs.persistence.entity.Position

sealed class FavoriteUiState {
    object Loading : FavoriteUiState()
    data class Success(val positions: List<Position>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}
