package com.urbanspts.urbanspts.model.SubCategoryModel


import com.google.gson.annotations.SerializedName

data class SubcategoriesData(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("providerId")
    val providerId: String = "",
    @SerializedName("subcategoryId")
    val subcategoryId: SubcategoryId = SubcategoryId(),
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)