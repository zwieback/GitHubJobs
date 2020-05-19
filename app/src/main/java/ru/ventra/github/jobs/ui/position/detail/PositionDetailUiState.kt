package ru.ventra.github.jobs.ui.position.detail

import ru.ventra.github.jobs.persistence.entity.Position

sealed class PositionDetailUiState {
    object Loading : PositionDetailUiState()
    data class Success(val position: Position) : PositionDetailUiState()
    data class Error(val message: String) : PositionDetailUiState()
}
