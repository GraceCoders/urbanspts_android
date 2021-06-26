package com.urbanspts.urbanspts.model.categoriesModel


import com.google.gson.annotations.SerializedName

data class Category(
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
    @SerializedName("type")
    val type: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)