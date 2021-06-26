package com.urbanspts.urbanspts.model.RequestModel.categoryRequest


import com.google.gson.annotations.SerializedName

data class CategoryDetailRequest(
    @SerializedName("categoryId")
    var categoryId: String = "",
    @SerializedName("startPoint")
    val startPoint: StartPoint = StartPoint()
)