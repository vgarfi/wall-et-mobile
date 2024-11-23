package com.example.wall_etmobile.features.transactions.datasource

import com.example.wall_etmobile.core.datasource.RemoteDataSource
import com.example.wall_etmobile.features.transactions.model.response.NetworkListTransactionInfo
import com.example.wall_etmobile.features.transactions.model.response.NetworkTransactionInfo
import com.example.wall_etmobile.features.transactions.service.TransactionApiService

class TransactionRemoteDataSource(
    private val transactionApiService: TransactionApiService
) : RemoteDataSource() {

    suspend fun getTransactions(): NetworkListTransactionInfo {
        return handleApiResponse {
            transactionApiService.getTransactions()
        }
    }

    suspend fun getTransaction(transactionId: Int): NetworkTransactionInfo {
        return handleApiResponse {
            transactionApiService.getTransaction(transactionId)
        }
    }
}