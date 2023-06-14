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

interface ApiRepository {

    suspend fun signIn(request : SignInRequest) : ApiResult<SignInResponse>

    suspend fun signUp(request: SignUpRequest) : ApiResult<SignUpResponse>

    suspend fun getHistory() : ApiResult<HistoryResponse>

    suspend fun getHistoryDetail(id : String) : ApiResult<HistoryDetailResponse>

    suspend fun predictPotato(image: MultipartBody.Part) : ApiResult<PredictResponse>
    suspend fun predictTomato(image: MultipartBody.Part) : ApiResult<PredictResponse>
    suspend fun predictPaprika(image: MultipartBody.Part) : ApiResult<PredictResponse>

}