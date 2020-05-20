package ru.ventra.github.jobs.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected var eventListener: OnFragmentEventListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentEventListener) {
            eventListener = context
        } else {
            error("${context.javaClass.simpleName} must implement OnFragmentEventListener")
        }
    }

    override fun onDetach() {
        eventListener = null
        super.onDetach()
    }
}
