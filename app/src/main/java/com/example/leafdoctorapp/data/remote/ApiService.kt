package com.example.leafdoctorapp.data.remote

import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignInResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("authentications")
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<SignInResponse>

    @POST("users")
    suspend fun signUp(
        @Body request: SignUpRequest
    ):Response<SignUpResponse>

    @GET("history")
    suspend fun getHistory() : Response<HistoryResponse>
}