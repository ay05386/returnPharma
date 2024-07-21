package com.example.returnpharma.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class Pharmacy(
    val id: String,
    val name: String
)

data class PharmacyDetails(
    val id: String,
    val name: String,
    val address: String,

)

data class Wholesaler(
    val id: String,
    val name: String
)

data class CreateReturnRequestRequest(
    val serviceType: String,
    val wholesalerId: String
)

data class ReturnRequest(
    val id: String,
    val createdAt: String,
    val numberOfItems: Int,
    val status: String,
    val serviceType: String,
    val associatedWholesaler: String
)

data class Item(
    val id: String,
    val ndc: String,
    val description: String,
    val manufacturer: String,
    val fullQuantity: Int,
    val partialQuantity: Int,
    val expirationDate: String,
    val lotNumber: String
)
