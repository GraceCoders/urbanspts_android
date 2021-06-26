package com.urbanspts.urbanspts.model.HomeModel


import com.google.gson.annotations.SerializedName

data class ProviderLocationX(
    @SerializedName("coordinates")
    val coordinates: List<Double> = listOf(),
    @SerializedName("type")
    val type: String = ""
)