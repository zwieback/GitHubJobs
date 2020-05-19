package ru.ventra.github.jobs.ui.position

import ru.ventra.github.jobs.persistence.entity.Position

sealed class PositionsUiState {
    object Loading : PositionsUiState()
    data class Success(val positions: List<Position>) : PositionsUiState()
    data class Error(val message: String) : PositionsUiState()
}
