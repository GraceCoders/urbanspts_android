package com.urbanspts.urbanspts.model.RequestModel.SubCategoryRequest


import com.google.gson.annotations.SerializedName

data class SubCategoryRequest(
    @SerializedName("startPoint")
    val startPoint: StartPoint = StartPoint(),
    @SerializedName("subcategoryId")
    var subcategoryId: String = ""
)