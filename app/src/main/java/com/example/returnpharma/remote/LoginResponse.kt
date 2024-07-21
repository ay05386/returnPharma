package com.example.returnpharma.remote

/*
data class LoginResponse(
    val accessToken: String,
    val tokenType: String = "Bearer", // Often "Bearer", but adjust if your API uses a different type
    val expiresIn: Int? = null, // Token expiration time in seconds, if provided by your API
    val refreshToken: String? = null, // If your API provides a refresh token
    val userId: String? = null // If your API returns the user ID
)
*/


data class LoginResponse(
    val accessToken: String
)
