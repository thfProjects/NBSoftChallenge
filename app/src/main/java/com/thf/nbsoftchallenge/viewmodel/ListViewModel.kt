package com.thf.nbsoftchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.thf.nbsoftchallenge.api.ProductRepository

class ListViewModel(repository: ProductRepository) : ViewModel() {

    init {
        repository.getProducts()
    }

    val products = repository.products
}