package com.urbanspts.urbanspts.model.categoriesModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categories")
    val categories: List<Category> = listOf()
)