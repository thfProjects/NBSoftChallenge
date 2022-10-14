package com.thf.nbsoftchallenge.application

import android.app.Application
import com.thf.nbsoftchallenge.api.ProductRepository

class MainApplication : Application(){

    val repository = ProductRepository()
}