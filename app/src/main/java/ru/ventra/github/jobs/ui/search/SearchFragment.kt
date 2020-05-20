package ru.ventra.github.jobs.ui.search

import android.os.Bundle
import android.view.View
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.SearchFragmentBinding
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.ui.base.BaseFragment

class SearchFragment : BaseFragment(R.layout.search_fragment) {

    private val binding by viewLifecycleLazy { SearchFragmentBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
