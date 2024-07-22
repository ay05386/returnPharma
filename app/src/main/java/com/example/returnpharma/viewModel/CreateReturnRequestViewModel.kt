package com.example.returnpharma.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.model.Pharmacy
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.repository.RxMaxRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CreateReturnRequestViewModel(private val repository: RxMaxRepository) : ViewModel() {
    private val _createRequestState = MutableStateFlow<CreateRequestState>(CreateRequestState.Idle)
    val createRequestState: StateFlow<CreateRequestState> = _createRequestState

    private val _pharmacies = MutableStateFlow<List<Pharmacy>>(emptyList())
    val pharmacies: StateFlow<List<Pharmacy>> = _pharmacies


    fun createReturnRequest(pharmacyId: String, serviceType: String, wholesalerId: String) {
        viewModelScope.launch {
            _createRequestState.value = CreateRequestState.Loading
            try {
                val request = CreateReturnRequestRequest(serviceType, wholesalerId)
                val result = repository.createReturnRequest(pharmacyId, request)
                result.fold(
                    onSuccess = { returnRequest ->
                        _createRequestState.value = CreateRequestState.Success(returnRequest)
                    },
                    onFailure = { error ->
                        _createRequestState.value = CreateRequestState.Error(error.message ?: "An error occurred")
                    }
                )
            } catch (e: Exception) {
                _createRequestState.value = CreateRequestState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun fetchPharmacies() {
        viewModelScope.launch {
            try {
                val response = repository.listPharmacies()
                if (response.isSuccessful) {
                    val pharmacies = response.body() ?: emptyList()
                    Log.d("CreateReturnRequestViewModel", "Fetched ${pharmacies.size} pharmacies")
                    _pharmacies.value = pharmacies
                } else {
                    Log.e("CreateReturnRequestViewModel", "Error fetching pharmacies: ${response.code()}")
                    _pharmacies.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("CreateReturnRequestViewModel", "Exception fetching pharmacies", e)
                _pharmacies.value = emptyList()
            }
        }
    }
}


sealed class CreateRequestState {
    object Idle : CreateRequestState()
    object Loading : CreateRequestState()
    data class Success(val returnRequest: ReturnRequest) : CreateRequestState()
    data class Error(val message: String) : CreateRequestState()
}




/*
class CreateReturnRequestViewModel : ViewModel() {
    private val repository = RxMaxRepository()

    private val _createRequestState = MutableStateFlow<CreateRequestState>(CreateRequestState.Idle)
    val createRequestState: StateFlow<CreateRequestState> = _createRequestState

    fun createReturnRequest(pharmacyId: String, serviceType: String, wholesalerId: String) {
        viewModelScope.launch {
            _createRequestState.value = CreateRequestState.Loading
            try {
                val request = CreateReturnRequestRequest(serviceType, wholesalerId)
                val result = repository.createReturnRequest(pharmacyId, request)
                if (result.isSuccess) {
                    _createRequestState.value = CreateRequestState.Success(result.getOrNull()!!)
                } else {
                    _createRequestState.value = CreateRequestState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _createRequestState.value = CreateRequestState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class CreateRequestState {
    object Idle : CreateRequestState()
    object Loading : CreateRequestState()
    data class Success(val returnRequest: ReturnRequest) : CreateRequestState()
    data class Error(val message: String) : CreateRequestState()
}*/