package com.urbanspts.urbanspts.model.CategoryDetailModel


import com.google.gson.annotations.SerializedName

data class ProviderLocation(
    @SerializedName("coordinates")
    val coordinates: List<Double> = listOf(),
    @SerializedName("type")
    val type: String = ""
)