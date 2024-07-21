package com.example.returnpharma.repository

import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.model.LoginRequest
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.networkModule.RetrofitClient
import com.example.returnpharma.remote.LoginResponse
import com.example.returnpharma.remote.RxMaxApi

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