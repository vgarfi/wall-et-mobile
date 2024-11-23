package com.example.wall_etmobile.features.transactions.service

import com.example.wall_etmobile.features.transactions.model.NetworkListTransactionInfo
import com.example.wall_etmobile.features.transactions.model.NetworkTransactionInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionApiService {
//    @POST("payment")

    @GET("payment")
    suspend fun getTransactions(): Response<NetworkListTransactionInfo>

    @GET("payment/{paymentId}")
    suspend fun getTransaction(@Path("paymentId") paymentId: Int): Response<NetworkTransactionInfo>
}