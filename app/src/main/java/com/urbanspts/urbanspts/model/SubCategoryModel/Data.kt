package com.urbanspts.urbanspts.model.SubCategoryModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("providerData")
    val providerData: List<ProviderData> = listOf(),
    @SerializedName("subcategoriesData")
    val subcategoriesData: SubcategoriesData = SubcategoriesData()
)