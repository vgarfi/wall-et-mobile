package com.example.wall_etmobile.features.transactions.repository

import com.example.wall_etmobile.features.transactions.datasource.TransactionRemoteDataSource
import com.example.wall_etmobile.features.transactions.model.TransactionInfo
import com.example.wall_etmobile.features.transactions.model.body.NetworkPaymentBody
import com.example.wall_etmobile.features.transactions.model.response.NetworkPaymentResponse
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TransactionRepository(
    private val remoteDataSource: TransactionRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val transactionsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var transactions: List<TransactionInfo> = emptyList()
    private var actualTransaction: TransactionInfo? = null

    suspend fun getTransactions(refresh: Boolean = false): List<TransactionInfo> {
        if (refresh || transactions.isEmpty()) {
            val result = remoteDataSource.getTransactions()
            // Thread-safe write to sports
            transactionsMutex.withLock {
                this.transactions = result.payments.map { it.asModel() }
            }
        }
        return transactionsMutex.withLock { this.transactions }
    }

    suspend fun getTransaction(transactionId: Int): TransactionInfo? {
        if (actualTransaction == null) {
            val result = remoteDataSource.getTransaction(transactionId = transactionId)
            // Thread-safe write to sports
            transactionsMutex.withLock {
                this.actualTransaction = result.asModel()
            }
        }
        return transactionsMutex.withLock { this.actualTransaction }
    }

    suspend fun addPayment(payment: NetworkPaymentBody): NetworkPaymentResponse {
        return remoteDataSource.addPayment(payment)
    }
}