package com.example.returnpharma.repository

import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.model.LoginRequest
import com.example.returnpharma.model.Pharmacy
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.networkModule.RetrofitClient
import com.example.returnpharma.remote.LoginResponse
import com.example.returnpharma.remote.RxMaxApi
import retrofit2.Response

class RxMaxRepository (){
    private val api = RetrofitClient.instance

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Login failed: ${e.message}"))
        }
    }

/*
    suspend fun createReturnRequest(request: CreateReturnRequestRequest): Response<ReturnRequest> {
        return api.createReturnRequest(request)
    }
*/
    suspend fun listPharmacies(): Response<List<Pharmacy>> {
        return api.listPharmacies()
    }



    suspend fun createReturnRequest(pharmacyId: String, request: CreateReturnRequestRequest): Result<ReturnRequest> {
        return try {
            val response = api.createReturnRequest(pharmacyId, request)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to create return request: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}