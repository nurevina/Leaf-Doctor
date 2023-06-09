package com.example.leafdoctorapp.data.model.networkmodel.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @field:SerializedName("username")
    val username: String? = "",

    @field:SerializedName("password")
    val password: String? = "",

    @field:SerializedName("fullname")
    val fullName : String? = "",

    @field:SerializedName("provinsi")
    val provinsi : String? = ""
)