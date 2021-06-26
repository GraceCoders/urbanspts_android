package com.urbanspts.urbanspts.model.CategoryDetailModel


import com.google.gson.annotations.SerializedName

data class ProviderData(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("providerId")
    val providerId: ProviderId = ProviderId(),
    @SerializedName("subcategoryId")
    val subcategoryId: SubcategoryId = SubcategoryId(),
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)