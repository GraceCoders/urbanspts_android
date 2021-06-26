package com.urbanspts.urbanspts.model.ContactUsModel


import com.google.gson.annotations.SerializedName

data class ContactUsResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)