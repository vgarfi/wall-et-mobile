package com.example.wall_etmobile.features.cards.repository

import com.example.wall_etmobile.features.cards.datasource.CardRemoteDataSource
import com.example.wall_etmobile.features.cards.model.CreditCard
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CardRepository(
    private val remoteDataSource: CardRemoteDataSource
) {
    private val cardsMutex = Mutex()
    private var creditCards: List<CreditCard> = emptyList()

    suspend fun getCards(refresh: Boolean = false): List<CreditCard> {
        if (refresh || creditCards.isEmpty()) {
            val result = remoteDataSource.getCards()
            cardsMutex.withLock {
                this.creditCards = result.map { it.asModel() }
            }
        }

        return cardsMutex.withLock { this.creditCards }
    }

    suspend fun addCard(creditCard: CreditCard) : CreditCard {
        val newCard = remoteDataSource.addCard(creditCard.asNetworkModel()).asModel()
        cardsMutex.withLock {
            this.creditCards = emptyList()
        }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            this.creditCards = emptyList()
        }
    }
}