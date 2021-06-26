package com.urbanspts.urbanspts.model.RequestModel.HomeRequest


import com.google.gson.annotations.SerializedName

data class HomeRequestModel(
    @SerializedName("startPoint")
    val startPoint: StartPoint = StartPoint()
)