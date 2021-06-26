package com.urbanspts.urbanspts.model.CategoryDetailModel


import com.google.gson.annotations.SerializedName

data class CategoriesData(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nameWithoutSpace")
    val nameWithoutSpace: String = "",
    @SerializedName("photo")
    val photo: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)