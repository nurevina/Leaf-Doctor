package com.example.leafdoctorapp.data.model.networkmodel.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class SignupResponseData(

    @field:SerializedName("userId")
    val userId: String? = null,

) : Parcelable
