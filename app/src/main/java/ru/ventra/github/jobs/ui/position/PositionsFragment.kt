package ru.ventra.github.jobs.ui.position

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionsFragmentBinding
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.ui.main.PositionsViewModel

class PositionsFragment : Fragment(R.layout.positions_fragment) {

    private val binding by viewLifecycleLazy { PositionsFragmentBinding.bind(requireView()) }
    private val vm: PositionsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
