package ru.ventra.github.jobs.ui.position.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionDetailFragmentBinding
import ru.ventra.github.jobs.extensions.gone
import ru.ventra.github.jobs.extensions.toast
import ru.ventra.github.jobs.extensions.viewLifecycleLazy
import ru.ventra.github.jobs.extensions.visible
import ru.ventra.github.jobs.ui.base.BaseFragment
import ru.ventra.github.jobs.utils.HtmlUtils
import ru.ventra.github.jobs.utils.ImageUtils

class PositionDetailFragment : BaseFragment(R.layout.position_detail_fragment) {

    private val binding by viewLifecycleLazy { PositionDetailFragmentBinding.bind(requireView()) }
    private val vm: PositionDetailViewModel by viewModel()
    private val args: PositionDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.uiState().observe(viewLifecycleOwner, Observer { uiState ->
            uiState?.let { render(it) }
        })
        vm.loadPosition(args.positionId)
    }

    private fun render(uiState: PositionDetailUiState) {
        when (uiState) {
            is PositionDetailUiState.Loading -> onLoad()
            is PositionDetailUiState.Success -> onSuccess(uiState)
            is PositionDetailUiState.Error -> onError(uiState)
        }
    }

    private fun onLoad() = with(binding) {
        typeLocation.gone()
        companyCard.gone()
        howToApplyCard.gone()
        description.gone()
        progressBar.visible()
    }

    private fun onSuccess(uiState: PositionDetailUiState.Success) = with(binding) {
        eventListener?.onChangeToolbarTitle(uiState.position.title)
        typeLocation.text = getString(
            R.string.position_detail_type_location,
            uiState.position.type,
            uiState.position.location
        )
        company.text = uiState.position.company
        companyUrl.text = uiState.position.companyUrl?.let { companyUrl ->
            HtmlUtils.fromHtml(companyUrl)
        }
        howToApplyValue.text = HtmlUtils.fromHtml(uiState.position.howToApply)
        description.text = HtmlUtils.fromHtml(uiState.position.description)

        ImageUtils.centerInsideWithNoPhoto(
            requireContext(),
            companyLogo,
            uiState.position.companyLogo
        )

        progressBar.gone()
        typeLocation.visible()
        companyCard.visible()
        howToApplyCard.visible()
        description.visible()
    }

    private fun onError(uiState: PositionDetailUiState.Error) = with(binding) {
        progressBar.gone()
        typeLocation.visible()
        companyCard.visible()
        howToApplyCard.visible()
        description.visible()
        context.toast(uiState.message)
    }
}
