package com.example.leafdoctorapp.ui.intro

import androidx.lifecycle.ViewModel
import com.example.leafdoctorapp.core.AppPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val appPreferenceManager: AppPreferenceManager
) : ViewModel(){

    val isFirstLaunch get() = appPreferenceManager.appFirstLaunch
    val loggedIn get() = !appPreferenceManager.accessToken.isNullOrEmpty()

    fun setNotFirstLaunch(){
        appPreferenceManager.appFirstLaunch = false
    }
}