package com.example.leafdoctorapp.ui.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leafdoctorapp.core.AppPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileVM @Inject constructor(
    private val appPreferenceManager: AppPreferenceManager
) : ViewModel() {

    fun deleteAllToken(){
        appPreferenceManager.resetToken()
    }
}