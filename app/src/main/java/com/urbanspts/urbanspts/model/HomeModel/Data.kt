package com.urbanspts.urbanspts.model.HomeModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categoriesData")
    val categoriesData: List<CategoriesData> = listOf(),
    @SerializedName("normals")
    val normals: List<Normal> = listOf(),
    @SerializedName("populars")
    val populars: List<Popular> = listOf()
)