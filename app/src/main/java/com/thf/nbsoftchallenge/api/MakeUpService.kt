package com.thf.nbsoftchallenge.api

import com.thf.nbsoftchallenge.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface MakeUpService {

    @GET("products.json?brand=maybelline")
    fun getProducts() : Call<List<Product>>

}