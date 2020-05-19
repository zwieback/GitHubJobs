package ru.ventra.github.jobs.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    fun uiState(): LiveData<T> = uiState

    protected val uiState: MutableLiveData<T> = MutableLiveData()
}
