package com.urbanspts.urbanspts.model.CategoryDetailModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categoriesData")
    val categoriesData: CategoriesData = CategoriesData(),
    @SerializedName("providerData")
    val providerData: List<ProviderData> = listOf(),
    @SerializedName("subcategoriesData")
    val subcategoriesData: List<SubcategoriesData> = listOf()
)