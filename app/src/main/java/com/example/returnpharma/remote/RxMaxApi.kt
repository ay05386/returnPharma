package com.example.returnpharma.remote

import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.model.Item
import com.example.returnpharma.model.LoginRequest
import com.example.returnpharma.model.Pharmacy
import com.example.returnpharma.model.PharmacyDetails
import com.example.returnpharma.model.ReturnRequest
import com.example.returnpharma.model.Wholesaler
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RxMaxApi {
    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("pharmacies/management")
    suspend fun listPharmacies(): Response<List<Pharmacy>>

    @GET("pharmacies/{pharmacyId}/full")
    suspend fun getPharmacy(@Path("pharmacyId") pharmacyId: String): Response<PharmacyDetails>

    @GET("pharmacies/{pharmacyId}/wholesalers")
    suspend fun listWholesalers(@Path("pharmacyId") pharmacyId: String): Response<List<Wholesaler>>

    @POST("pharmacies/{pharmacyId}/returnrequests")
    suspend fun createReturnRequest(
        @Path("pharmacyId") pharmacyId: String,
        @Body request: CreateReturnRequestRequest
    ): Response<ReturnRequest>

    @GET("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}")
    suspend fun getReturnRequest(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String
    ): Response<ReturnRequest>

    @GET("pharmacies/{pharmacyId}/returnrequests")
    suspend fun listReturnRequests(@Path("pharmacyId") pharmacyId: String): Response<List<ReturnRequest>>

    @POST("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}/items")
    suspend fun addItem(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String,
        @Body item: Item
    ): Response<Item>

    @PUT("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}/items/{itemId}")
    suspend fun updateItem(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String,
        @Path("itemId") itemId: String,
        @Body item: Item
    ): Response<Item>

    @GET("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}/items/{itemId}")
    suspend fun getItem(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String,
        @Path("itemId") itemId: String
    ): Response<Item>

    @GET("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}/items")
    suspend fun listItems(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String
    ): Response<List<Item>>

    @DELETE("pharmacies/{pharmacyId}/returnrequests/{returnRequestId}/items/{itemId}")
    suspend fun deleteItem(
        @Path("pharmacyId") pharmacyId: String,
        @Path("returnRequestId") returnRequestId: String,
        @Path("itemId") itemId: String
    ): Response<Unit>
}
