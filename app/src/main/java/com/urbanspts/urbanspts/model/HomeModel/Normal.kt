package com.urbanspts.urbanspts.model.HomeModel


import com.google.gson.annotations.SerializedName

data class Normal(
    @SerializedName("categoryId")
    val categoryId: CategoryId = CategoryId(),
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("providerId")
    val providerId: ProviderId = ProviderId(),
    @SerializedName("providerType")
    val providerType: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)