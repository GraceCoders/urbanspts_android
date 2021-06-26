package com.urbanspts.urbanspts.model.ChangePasswordModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("profileImage")
    val profileImage: Any = Any()
)