package com.example.leafdoctorapp.ui.camera

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafdoctorapp.data.model.networkmodel.response.PredictResponse
import com.example.leafdoctorapp.data.remote.ApiRepository
import com.example.leafdoctorapp.data.remote.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class CameraVM @Inject constructor(
    @ApplicationContext applicationContext: Context,
    private val repository: ApiRepository
) : ViewModel() {

    private val _onUploadImage = MutableLiveData<PredictResponse>()
    val onUploadImage: LiveData<PredictResponse> get() = _onUploadImage


    private val _onGetError = MutableLiveData<Error>()
    val onGetError: LiveData<Error> get() = _onGetError

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> get() = _onLoading

    private fun showLoading() {
        _onLoading.value = true
    }

    private fun dismissLoading() {
        _onLoading.value = false
    }

    fun predictTomato(image: MultipartBody.Part) {
        showLoading()
        viewModelScope.launch {
            repository.predictTomato(image).fold(
                onSuccess = {
                    dismissLoading()
                    _onUploadImage.value = it
                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }

    fun predictPaprika(image: MultipartBody.Part) {
        showLoading()
        viewModelScope.launch {
            repository.predictPaprika(image).fold(
                onSuccess = {
                    dismissLoading()
                    _onUploadImage.value = it
                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }

    fun predictPotato(image: MultipartBody.Part) {
        showLoading()
        viewModelScope.launch {
            repository.predictPotato(image).fold(
                onSuccess = {
                    dismissLoading()
                    _onUploadImage.value = it
                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }

}