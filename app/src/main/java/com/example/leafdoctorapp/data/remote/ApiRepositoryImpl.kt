package com.example.leafdoctorapp.data.remote

import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryDetailResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.PredictResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun signIn(request: SignInRequest) =
        handleApi { apiService.signIn(request) }

    override suspend fun signUp(request: SignUpRequest): ApiResult<SignUpResponse> =
        handleApi { apiService.signUp(request) }

    override suspend fun getHistory(): ApiResult<HistoryResponse> =
        handleApi { apiService.getHistory() }

    override suspend fun getHistoryDetail(id: String): ApiResult<HistoryDetailResponse> =
        handleApi { apiService.getHistoryDetail(id) }

    override suspend fun predictPotato(image: MultipartBody.Part): ApiResult<PredictResponse> =
        handleApi { apiService.predictPotato(image) }


    override suspend fun predictTomato(image: MultipartBody.Part): ApiResult<PredictResponse> {
        return handleApi { apiService.predictTomato(image) }
    }

    override suspend fun predictPaprika(image: MultipartBody.Part): ApiResult<PredictResponse> {
        return handleApi { apiService.predictPaprika(image) }
    }


}