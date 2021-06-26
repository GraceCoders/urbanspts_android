package com.urbanspts.urbanspts.model.registerModel


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
    @SerializedName("password")
    val password: String = "",
    @SerializedName("profileImage")
    val profileImage: Any = Any(),
    @SerializedName("role")
    val role: String = "",
    @SerializedName("token")
    val token: String = "",
    @SerializedName("__v")
    val v: Int = 0
)