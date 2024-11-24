package com.example.wall_etmobile.features.transactions.service

import com.example.wall_etmobile.features.transactions.model.body.NetworkPaymentBody
import com.example.wall_etmobile.features.transactions.model.response.NetworkLinkPaymentResponse
import com.example.wall_etmobile.features.transactions.model.response.NetworkListTransactionInfo
import com.example.wall_etmobile.features.transactions.model.response.NetworkPaymentResponse
import com.example.wall_etmobile.features.transactions.model.response.NetworkTransactionInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface TransactionApiService {
//    @POST("payment")

    @GET("payment")
    suspend fun getTransactions(): Response<NetworkListTransactionInfo>

    @GET("payment/{paymentId}")
    suspend fun getTransaction(@Path("paymentId") paymentId: Int): Response<NetworkTransactionInfo>

    @POST("payment")
    suspend fun addPayment(@Body payment: NetworkPaymentBody): Response<NetworkPaymentResponse>

    @POST("payment/{linkUuid}")
    suspend fun addLinkPayment(@Path("linkUuid") linkUuid: UUID, @Body payment: NetworkPaymentBody): Response<NetworkLinkPaymentResponse>

    @GET("payment/link/{linkUuid}")
    suspend fun getLinkPayment(@Path("linkUuid") linkUuid: UUID): Response<NetworkLinkPaymentResponse>



}