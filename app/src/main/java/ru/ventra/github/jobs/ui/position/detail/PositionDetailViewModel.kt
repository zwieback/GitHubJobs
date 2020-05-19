package ru.ventra.github.jobs.ui.position.detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.ventra.github.jobs.persistence.repository.PositionLocalRepository
import ru.ventra.github.jobs.ui.base.BaseViewModel

class PositionDetailViewModel : BaseViewModel<PositionDetailUiState>() {

    private val localRepository by inject(PositionLocalRepository::class.java)

    fun loadPosition(id: String) {
        uiState.value = PositionDetailUiState.Loading
        viewModelScope.launch {
            try {
                localRepository.getPositionById(id)?.let { position ->
                    uiState.value = PositionDetailUiState.Success(position)
                } ?: kotlin.run {
                    uiState.value = PositionDetailUiState.Error("Job $id is not found")
                }
            } catch (e: Exception) {
                uiState.value = PositionDetailUiState.Error("Failed to get data from the database")
            }
        }
    }
}
