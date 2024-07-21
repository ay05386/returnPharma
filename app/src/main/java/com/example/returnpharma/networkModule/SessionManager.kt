package com.example.returnpharma.networkModule


object SessionManager {
    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    fun getToken(): String? {
        return token
    }

    fun clearToken() {
        token = null
    }
}