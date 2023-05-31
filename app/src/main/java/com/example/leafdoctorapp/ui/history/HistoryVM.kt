package com.example.leafdoctorapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryVM : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "History"
    }
    val text: LiveData<String> = _text
}