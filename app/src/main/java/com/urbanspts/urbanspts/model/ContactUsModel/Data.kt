package com.urbanspts.urbanspts.model.ContactUsModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)