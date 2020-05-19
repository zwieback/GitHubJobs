package ru.ventra.github.jobs.ui.position

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionsFragmentBinding
import ru.ventra.github.jobs.extensions.gone
import ru.ventra.github.jobs.extensions.toast
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.extensions.visible
import ru.ventra.github.jobs.ui.main.PositionsViewModel

class PositionsFragment : Fragment(R.layout.positions_fragment) {

    private val binding by viewLifecycleLazy { PositionsFragmentBinding.bind(requireView()) }
    private val vm: PositionsViewModel by viewModel()
    private val adapter by lazy { PositionsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.uiState().observe(viewLifecycleOwner, Observer { uiState ->
            uiState?.let { render(it) }
        })
        binding.recyclerView.adapter = adapter
        vm.loadPositions()
    }

    private fun render(uiState: PositionsUiState) {
        when (uiState) {
            is PositionsUiState.Loading -> onLoad()
            is PositionsUiState.Success -> onSuccess(uiState)
            is PositionsUiState.Error -> onError(uiState)
        }
    }

    private fun onLoad() = with(binding) {
        recyclerView.gone()
        progressBar.visible()
    }

    private fun onSuccess(uiState: PositionsUiState.Success) = with(binding) {
        adapter.replacePositions(uiState.positions)
        progressBar.gone()
        recyclerView.visible()
    }

    private fun onError(uiState: PositionsUiState.Error) = with(binding) {
        adapter.replacePositions(emptyList())
        progressBar.gone()
        recyclerView.visible()
        context.toast(uiState.message)
    }
}
