package com.example.leafdoctorapp.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafdoctorapp.core.AppPreferenceManager
import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.data.model.networkmodel.response.SignInResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.SignUpResponse
import com.example.leafdoctorapp.data.remote.ApiRepository
import com.example.leafdoctorapp.data.remote.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationVM @Inject constructor(
    private val apiRepository: ApiRepository,
    private val pref: AppPreferenceManager
) : ViewModel() {

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

    private val _onSignedIn = MutableLiveData<SignInResponse>()
    val onSignedIn: LiveData<SignInResponse> get() = _onSignedIn

    private val _onSignedUp = MutableLiveData<SignUpResponse>()
    val onSignedUp: LiveData<SignUpResponse> get() = _onSignedUp




    fun signIn(request: SignInRequest) {
        showLoading()
        viewModelScope.launch {
            apiRepository.signIn(request).fold(
                onSuccess = {
                    dismissLoading()
                    pref.saveAccessToken(it.data?.accessToken.orEmpty())
                    pref.saveRefreshToken(it.data?.refreshToken.orEmpty())
                    _onSignedIn.value = it

                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }

    fun signUp(request: SignUpRequest) {
        showLoading()
        viewModelScope.launch {
            apiRepository.signUp(request).fold(
                onSuccess = {
                    dismissLoading()
                    _onSignedUp.value = it

                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }
}