package ru.ventra.github.jobs.ui.search

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * [Copied from here](https://android.jlelse.eu/implementing-search-on-type-in-android-with-coroutines-ab117c8f13a4)
 */
class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val debouncePeriod: Long = 750,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {

    private val coroutineScope: CoroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}
