package com.urbanspts.urbanspts.model.SubCategoryModel


import com.google.gson.annotations.SerializedName

data class Categoriesparse(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)