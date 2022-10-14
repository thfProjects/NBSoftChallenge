package com.thf.nbsoftchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val brand: String,
    @SerializedName("image_link")
    val imageLink: String,
    val name: String,
    val price: String
) : Parcelable