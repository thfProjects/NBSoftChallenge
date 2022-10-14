package com.thf.nbsoftchallenge.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.thf.nbsoftchallenge.model.Product

class DetailsViewModel(private val state: SavedStateHandle) : ViewModel() {

    val product = state.get<Product>("product")
}