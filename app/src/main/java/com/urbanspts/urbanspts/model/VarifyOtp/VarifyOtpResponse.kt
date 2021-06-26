package com.urbanspts.urbanspts.model.VarifyOtp


import com.google.gson.annotations.SerializedName

data class VarifyOtpResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)