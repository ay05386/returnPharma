package com.example.returnpharma.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.repository.RxMaxRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class CreateReturnRequestViewModel : ViewModel() {
    private val repository = RxMaxRepository() // Assuming you have this repository

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
}