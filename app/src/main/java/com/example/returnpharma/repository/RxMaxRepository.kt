package com.example.returnpharma.repository

import com.example.returnpharma.model.LoginRequest
import com.example.returnpharma.networkModule.RetrofitClient
import com.example.returnpharma.remote.LoginResponse

class RxMaxRepository {
    private val api = RetrofitClient.instance

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}