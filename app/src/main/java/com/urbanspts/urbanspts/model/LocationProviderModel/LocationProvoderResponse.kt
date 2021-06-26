package com.urbanspts.urbanspts.model.LocationProviderModel


import com.google.gson.annotations.SerializedName

data class LocationProvoderResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)