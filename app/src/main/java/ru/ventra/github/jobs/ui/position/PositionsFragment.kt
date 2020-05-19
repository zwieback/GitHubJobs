package ru.ventra.github.jobs.ui.position

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.ui.main.PositionsViewModel

class PositionsFragment : Fragment() {

    companion object {
        fun newInstance() = PositionsFragment()
    }

    private lateinit var viewModel: PositionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.positions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PositionsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
