package com.thf.nbsoftchallenge.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thf.nbsoftchallenge.application.MainApplication
import com.thf.nbsoftchallenge.viewmodel.ListViewModel

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val application: MainApplication) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(application.repository) as T
    }
}