package com.example.leafdoctorapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafdoctorapp.data.model.networkmodel.response.DataHistory
import com.example.leafdoctorapp.data.model.networkmodel.response.History
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryData
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryDetailResponse
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryItem
import com.example.leafdoctorapp.data.remote.ApiRepository
import com.example.leafdoctorapp.data.remote.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryVM @Inject constructor(
    private val apiRepository: ApiRepository
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

    private val _onGetHistoryData = MutableLiveData<HistoryData>()
    val onGetHistoryData: LiveData<HistoryData> get() = _onGetHistoryData

    private val _onGetHistoryDetail = MutableLiveData<DataHistory>()
    val nGetHistoryDetail: LiveData<DataHistory> get() = _onGetHistoryDetail

    fun getHistory() {
        showLoading()
        viewModelScope.launch {
            apiRepository.getHistory().fold(
                onSuccess = {
                    dismissLoading()
                    _onGetHistoryData.value = it.data!!
                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }

    fun getHistoryDetail(id: String) {
        showLoading()
        viewModelScope.launch {
            apiRepository.getHistoryDetail(id).fold(
                onSuccess = {
                    dismissLoading()
                    _onGetHistoryDetail.value = it.data!!
                },
                onError = {
                    dismissLoading()
                    _onGetError.value = Error(it?.message)
                }
            )
        }
    }
}