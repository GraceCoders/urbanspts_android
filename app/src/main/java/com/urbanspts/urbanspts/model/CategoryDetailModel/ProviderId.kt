package com.urbanspts.urbanspts.model.CategoryDetailModel


import com.google.gson.annotations.SerializedName

data class ProviderId(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("categories")
    val categories: String = "",
    @SerializedName("categoriesparse")
    val categoriesparse: List<Categoriesparse> = listOf(),
    @SerializedName("city")
    val city: String = "",
    @SerializedName("closingDay")
    val closingDay: String = "",
    @SerializedName("closingTime")
    val closingTime: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("coverPhoto")
    val coverPhoto: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("distance")
    val distance: Double = 0.0,
    @SerializedName("email")
    val email: String = "",
    @SerializedName("facebook")
    val facebook: String = "",
    @SerializedName("galleries")
    val galleries: List<String> = listOf(),
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("instagram")
    val instagram: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nameWithoutSpace")
    val nameWithoutSpace: String = "",
    @SerializedName("openingDay")
    val openingDay: String = "",
    @SerializedName("openingTime")
    val openingTime: String = "",
    @SerializedName("phoneNumber")
    val phoneNumber: String = "",
    @SerializedName("photo")
    val photo: String = "",
    @SerializedName("providerLocation")
    val providerLocation: ProviderLocation = ProviderLocation(),
    @SerializedName("providerType")
    val providerType: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("stateId")
    val stateId: String = "",
    @SerializedName("subcategories")
    val subcategories: String = "",
    @SerializedName("subcategoriesparse")
    val subcategoriesparse: List<Subcategoriesparse> = listOf(),
    @SerializedName("twitter")
    val twitter: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("youtube")
    val youtube: String = ""
)