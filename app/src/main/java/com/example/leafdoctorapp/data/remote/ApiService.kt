package com.example.leafdoctorapp.data.remote

import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryDetailResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.PredictResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignInResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("history/{id}")
    suspend fun getHistoryDetail(
        @Path("id") id : String
    ) : Response<HistoryDetailResponse>

    @Multipart
    @POST("predictpaprika")
    suspend fun predictPaprika(
        @Part image: MultipartBody.Part
    ) : Response<PredictResponse>

    @Multipart
    @POST("predictpotato")
    suspend fun predictPotato(
        @Part image: MultipartBody.Part
    ) : Response<PredictResponse>

    @Multipart
    @POST("predicttomato")
    suspend fun predictTomato(
        @Part image: MultipartBody.Part
    ) : Response<PredictResponse>
}