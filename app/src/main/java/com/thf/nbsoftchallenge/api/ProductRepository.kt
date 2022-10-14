package com.thf.nbsoftchallenge.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thf.nbsoftchallenge.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {

    companion object {
        private const val baseURL = "https://makeup-api.herokuapp.com/api/v1/"
        private const val maxRetries = 10
    }

    private val makeUpService = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MakeUpService::class.java)

    val products: LiveData<List<Product>> = MutableLiveData(ArrayList())

    fun getProducts() {
        makeUpService.getProducts().enqueue(object : CallbackWithRetries<List<Product>> (maxRetries){

            override fun onResponse(
                call: Call<List<Product>>?,
                response: Response<List<Product>>?
            ) {
                response?.body()?.let {
                    products.setValue(it)
                }
            }

            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {

            }
        })
    }

    private fun <T> LiveData<T>.setValue (value: T) {
        (this as MutableLiveData<T>).setValue(value)
    }

    private abstract class CallbackWithRetries<T> (val retries: Int) : Callback<T> {
        private var retryCount = 0

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if ((response == null || !response.isSuccessful) && retryCount < retries) retry(call)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            if (retryCount < retries) retry(call)
        }

        private fun retry (call: Call<T>?) {
            call?.clone()?.enqueue(this)
            retryCount++
        }
    }
}