package com.example.leafdoctorapp.data.remote

import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignInResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse

interface ApiRepository {

    suspend fun signIn(request : SignInRequest) : ApiResult<SignInResponse>

    suspend fun signUp(request: SignUpRequest) : ApiResult<SignUpResponse>

    suspend fun getHistory() : ApiResult<HistoryResponse>

}