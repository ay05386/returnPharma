package com.example.returnpharma.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.returnpharma.repository.RxMaxRepository
class CreateReturnRequestViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateReturnRequestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateReturnRequestViewModel(RxMaxRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}