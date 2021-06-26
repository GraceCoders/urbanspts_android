package com.urbanspts.urbanspts.model.UpdateImageModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("fname")
    val fname: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("lname")
    val lname: String = "",
    @SerializedName("profileImage")
    val profileImage: String = "",
    @SerializedName("role")
    val role: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("__v")
    val v: Int = 0
)