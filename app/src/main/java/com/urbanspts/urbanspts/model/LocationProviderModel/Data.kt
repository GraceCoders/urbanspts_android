package com.urbanspts.urbanspts.model.LocationProviderModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("providerId")
    val providerId: List<ProviderId> = listOf()
)