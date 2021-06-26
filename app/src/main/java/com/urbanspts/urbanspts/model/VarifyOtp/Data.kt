package com.urbanspts.urbanspts.model.VarifyOtp


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("email")
    val email: String = "",
    @SerializedName("OTP")
    val oTP: String = ""
)