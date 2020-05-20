package ru.ventra.github.jobs.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.SearchFragmentBinding
import ru.ventra.github.jobs.extensions.gone
import ru.ventra.github.jobs.extensions.toast
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.extensions.visible
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.ui.base.BaseFragment
import ru.ventra.github.jobs.ui.position.OnPositionClickListener
import ru.ventra.github.jobs.ui.position.PositionsAdapter

class SearchFragment : BaseFragment(R.layout.search_fragment), OnPositionClickListener {

    private val binding by viewLifecycleLazy { SearchFragmentBinding.bind(requireView()) }
    private val vm: SearchViewModel by viewModel()
    private val adapter by lazy { PositionsAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.uiState().observe(viewLifecycleOwner, Observer { uiState ->
            uiState?.let { render(it) }
        })
        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(this.lifecycle) { newText ->
                newText?.let { search ->
                    if (search.isNotBlank()) {
                        vm.searchPositions(search.trim())
                    } else {
                        vm.cancelSearch()
                        adapter.clearAllSections()
                    }
                }
            }
        )
    }

    override fun onPositionClick(position: Position) {
        val directions = SearchFragmentDirections.actionPositionsToDetail(position.id)
        findNavController().navigate(directions)
    }

    private fun render(uiState: SearchUiState) {
        when (uiState) {
            is SearchUiState.Loading -> onLoad()
            is SearchUiState.Success -> onSuccess(uiState)
            is SearchUiState.Error -> onError(uiState)
        }
    }

    private fun onLoad() = with(binding) {
        recyclerView.gone()
        progressBar.visible()
    }

    private fun onSuccess(uiState: SearchUiState.Success) = with(binding) {
        adapter.replacePositions(uiState.positions)
        progressBar.gone()
        recyclerView.visible()
    }

    private fun onError(uiState: SearchUiState.Error) = with(binding) {
        adapter.replacePositions(emptyList())
        progressBar.gone()
        recyclerView.visible()
        context.toast(uiState.message)
    }
}
