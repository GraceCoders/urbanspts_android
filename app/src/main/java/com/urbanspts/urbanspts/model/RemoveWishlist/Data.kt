package com.urbanspts.urbanspts.model.RemoveWishlist


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("deletedCount")
    val deletedCount: Int = 0,
    @SerializedName("n")
    val n: Int = 0,
    @SerializedName("ok")
    val ok: Int = 0
)