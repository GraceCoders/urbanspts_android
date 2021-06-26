package com.urbanspts.urbanspts.model.WishlistModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("providerId")
    val providerId: ProviderId = ProviderId(),
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("userId")
    val userId: String = ""
)