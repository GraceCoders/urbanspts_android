package com.urbanspts.urbanspts.model.ForgetPassword


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("profileImage")
    val profileImage: Any = Any()
)