package ru.ventra.github.jobs.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionsFragmentBinding
import ru.ventra.github.jobs.extensions.gone
import ru.ventra.github.jobs.extensions.toast
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.extensions.visible
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.ui.base.BaseFragment
import ru.ventra.github.jobs.ui.position.OnPositionClickListener

class FavoriteFragment : BaseFragment(R.layout.favorite_fragment), OnPositionClickListener {

    private val binding by viewLifecycleLazy { PositionsFragmentBinding.bind(requireView()) }
    private val vm: FavoriteViewModel by viewModel()
    private val adapter by lazy { FavoriteAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.uiState().observe(viewLifecycleOwner, Observer { uiState ->
            uiState?.let { render(it) }
        })
        binding.recyclerView.adapter = adapter
        vm.loadPositions()
    }

    override fun onPositionClick(position: Position) {
        vm.toggleFavorite(position)
    }

    private fun render(uiState: FavoriteUiState) {
        when (uiState) {
            is FavoriteUiState.Loading -> onLoad()
            is FavoriteUiState.Success -> onSuccess(uiState)
            is FavoriteUiState.Error -> onError(uiState)
        }
    }

    private fun onLoad() = with(binding) {
        recyclerView.gone()
        progressBar.visible()
    }

    private fun onSuccess(uiState: FavoriteUiState.Success) = with(binding) {
        eventListener?.onChangeToolbarTitle(getString(R.string.app_name))
        adapter.replacePositions(uiState.positions)
        progressBar.gone()
        recyclerView.visible()
    }

    private fun onError(uiState: FavoriteUiState.Error) = with(binding) {
        adapter.replacePositions(emptyList())
        progressBar.gone()
        recyclerView.visible()
        context.toast(uiState.message)
    }
}
