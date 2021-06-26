package com.urbanspts.urbanspts.model.RequestModel.ProviderRequest


import com.google.gson.annotations.SerializedName

data class LocationProviderRequest(
    @SerializedName("startPoint")
    val startPoint: StartPoint = StartPoint(),
    @SerializedName("stateId")
    var stateId: String = ""
)