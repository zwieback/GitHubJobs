package ru.ventra.github.jobs.ui.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import ru.ventra.github.jobs.network.repository.PositionNetworkRepository
import ru.ventra.github.jobs.persistence.repository.PositionLocalRepository
import ru.ventra.github.jobs.ui.base.BaseViewModel

class SearchViewModel : BaseViewModel<SearchUiState>() {

    private val networkRepository by inject(PositionNetworkRepository::class.java)
    private val localRepository by inject(PositionLocalRepository::class.java)

    fun searchPositions(search: String) {
        uiState.value = SearchUiState.Loading
        viewModelScope.launch {
            if (!searchFromLocalCache(search)) {
                searchFromNetwork(search)
            }
        }
    }

    private suspend fun searchFromLocalCache(search: String): Boolean {
        try {
            val positions = localRepository.searchPositions(search)
            if (positions.isNotEmpty()) {
                uiState.value = SearchUiState.Success(positions)
                return true
            }
        } catch (e: Exception) {
            uiState.value = SearchUiState.Error("Failed to get data from the database")
        }
        return false
    }

    private suspend fun searchFromNetwork(search: String) {
        try {
            val positions = withContext(Dispatchers.IO) {
                networkRepository.searchPositions(search)
            }
            if (positions.isNotEmpty()) {
                localRepository.savePositions(positions)
                uiState.value = SearchUiState.Success(positions)
            } else {
                uiState.value = SearchUiState.Error("No jobs found")
            }
        } catch (e: Exception) {
            uiState.value = SearchUiState.Error("Failed to get data from the network")
        }
    }

    fun cancelSearch() {
        if (viewModelScope.isActive) {
            viewModelScope.cancel(CancellationException())
            uiState.value = SearchUiState.Error("Search cancelled")
        }
    }
}
