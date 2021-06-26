package com.urbanspts.urbanspts.model.ChangePasswordModel


import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)