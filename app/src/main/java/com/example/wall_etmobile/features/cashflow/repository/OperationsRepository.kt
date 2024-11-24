package com.example.wall_etmobile.features.cashflow.repository

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.wall_etmobile.R
import com.example.wall_etmobile.features.cards.model.CreditCard
import com.example.wall_etmobile.features.transactions.model.TransactionType
import com.example.wall_etmobile.features.transactions.model.body.NetworkPaymentBody
import com.example.wall_etmobile.features.transactions.model.body.PaymentType
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class OperationsRepository() {

    private val operationsMutex = Mutex()
    private var selectedMethodPayment : CreditCard? = null
    private var receiverID : String? = null
    private var amount : String? = null
    private var description : String? = null
    private var operationType : TransactionType? = null


    suspend fun makePayment(context: Context): NetworkPaymentBody {
        return when {
            operationType == TransactionType.CHARGE -> {
                NetworkPaymentBody(
                    amount = amount?.toDouble(),
                    description = if (description.isNullOrEmpty()) context.getString(R.string.default_message_payment) else description.toString(),
                    type = PaymentType.LINK.label,
                    cardId = null,
                    receiverEmail = null
                )
            }
            selectedMethodPayment?.number == "0000 0000 0000 0000" -> {
                NetworkPaymentBody(
                    amount = amount?.toDouble(),
                    description = if (description.isNullOrEmpty()) context.getString(R.string.default_message_payment) else description.toString(),
                    type = PaymentType.BALANCE.label,
                    receiverEmail = receiverID ?: "",
                    cardId = null
                )
            }
            else -> {
                NetworkPaymentBody(
                    amount = amount?.toDouble(),
                    description = if (description.isNullOrEmpty()) context.getString(R.string.default_message_payment) else description.toString(),
                    type = PaymentType.CARD.label,
                    receiverEmail = receiverID ?: "",
                    cardId = selectedMethodPayment?.id
                )
            }
        }
    }


    suspend fun setSelectedMethodPayment(creditCard: CreditCard?): CreditCard? {
        operationsMutex.withLock {
            this.selectedMethodPayment = creditCard
        }
        return this.selectedMethodPayment
    }

    suspend fun getSelectedMethodPayment(): CreditCard? {
        return operationsMutex.withLock { this.selectedMethodPayment }
    }

    suspend fun setReceiverID(id: String?): String? {
        operationsMutex.withLock {
            this.receiverID = id
        }
        return this.receiverID
    }

    suspend fun getReceiverID(): String? {
        return operationsMutex.withLock { this.receiverID }
    }

    suspend fun setAmount(amount: String?): String? {
        operationsMutex.withLock {
            this.amount = amount
        }
        return  this.amount
    }

    suspend fun getAmount(): String? {
        return operationsMutex.withLock { this.amount }
    }

    suspend fun setDescription(description: String?): String? {
        operationsMutex.withLock {
            this.description = description
        }
        return this.description
    }

    suspend fun getDescription(): String? {
        return operationsMutex.withLock { this.description }
    }

    suspend fun setOperationType(operationType: TransactionType?): TransactionType? {
        operationsMutex.withLock {
            this.operationType = operationType
        }
        return this.operationType
    }

    suspend fun getOperationType(): TransactionType? {
        return operationsMutex.withLock { this.operationType }
    }

}

