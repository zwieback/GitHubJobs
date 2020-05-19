package ru.ventra.github.jobs.ui.position

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import ru.ventra.github.jobs.network.repository.PositionNetworkRepository
import ru.ventra.github.jobs.persistence.repository.PositionLocalRepository
import ru.ventra.github.jobs.ui.base.BaseViewModel

class PositionsViewModel : BaseViewModel<PositionsUiState>() {

    private val networkRepository by inject(PositionNetworkRepository::class.java)
    private val localRepository by inject(PositionLocalRepository::class.java)

    fun loadPositions() {
        uiState.value = PositionsUiState.Loading
        viewModelScope.launch {
            try {
                val positions = withContext(Dispatchers.IO) {
                    networkRepository.getAllPositions()
                }
                if (positions.isNotEmpty()) {
                    localRepository.savePositions(positions)
                    uiState.value = PositionsUiState.Success(positions)
                } else {
                    uiState.value = PositionsUiState.Error("No jobs found")
                }
            } catch (e: Exception) {
                uiState.value = PositionsUiState.Error("Failed to get data from the network")
            }
        }
    }
}
