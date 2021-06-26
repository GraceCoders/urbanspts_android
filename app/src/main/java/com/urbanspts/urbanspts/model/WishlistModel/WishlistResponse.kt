package com.urbanspts.urbanspts.model.WishlistModel


import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)