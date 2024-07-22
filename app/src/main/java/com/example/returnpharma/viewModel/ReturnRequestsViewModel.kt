package com.example.returnpharma.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.networkModule.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReturnRequestsViewModel : ViewModel() {
    private val _returnRequests = MutableStateFlow<List<ReturnRequest>>(emptyList())
    val returnRequests: StateFlow<List<ReturnRequest>> get() = _returnRequests

    init {
        fetchReturnRequests("yourPharmacyId")
    }

    private fun fetchReturnRequests(pharmacyId: String) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.listReturnRequests(pharmacyId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _returnRequests.value = it
                }
            }
        }
    }
}
