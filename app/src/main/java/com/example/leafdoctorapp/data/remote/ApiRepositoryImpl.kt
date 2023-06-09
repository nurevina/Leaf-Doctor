package com.example.leafdoctorapp.data.remote

import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignInResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):ApiRepository {
    override suspend fun signIn(request: SignInRequest)=
        handleApi { apiService.signIn(request) }

    override suspend fun signUp(request: SignUpRequest): ApiResult<SignUpResponse> =
        handleApi { apiService.signUp(request) }

    override suspend fun getHistory(): ApiResult<HistoryResponse> =
        handleApi { apiService.getHistory() }

}