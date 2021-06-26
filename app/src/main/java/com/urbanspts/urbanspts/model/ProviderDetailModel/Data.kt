package com.urbanspts.urbanspts.model.ProviderDetailModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("isWishList")
    val isWishList: Int = 0,
    @SerializedName("providerId")
    val providerId: ProviderId = ProviderId()
)