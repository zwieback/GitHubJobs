package ru.ventra.github.jobs.ui.favorite

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.persistence.repository.PositionLocalRepository
import ru.ventra.github.jobs.ui.base.BaseViewModel

class FavoriteViewModel : BaseViewModel<FavoriteUiState>() {

    private val localRepository by inject(PositionLocalRepository::class.java)

    fun loadPositions() {
        uiState.value = FavoriteUiState.Loading
        viewModelScope.launch {
            try {
                val positions = localRepository.getAllPositions()
                if (positions.isNotEmpty()) {
                    uiState.value = FavoriteUiState.Success(positions)
                } else {
                    uiState.value = FavoriteUiState.Error("No positions in the database")
                }
            } catch (e: Exception) {
                uiState.value = FavoriteUiState.Error("Failed to get data from the database")
            }
        }
    }

    fun toggleFavorite(position: Position) {
        position.favorite = !position.favorite
        viewModelScope.launch {
            try {
                localRepository.updatePosition(position)
            } catch (e: Exception) {
                uiState.value = FavoriteUiState.Error("Failed to update data in the database")
            }
        }
    }
}
