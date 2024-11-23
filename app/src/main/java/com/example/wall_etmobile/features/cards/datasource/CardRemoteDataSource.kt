package com.example.wall_etmobile.features.cards.datasource


import com.example.wall_etmobile.core.datasource.RemoteDataSource
import com.example.wall_etmobile.features.cards.model.NetworkCard
import com.example.wall_etmobile.features.cards.service.CardApiService

class CardRemoteDataSource(
    private val walletApiService: CardApiService
) : RemoteDataSource() {

    suspend fun getCards(): List<NetworkCard> {
        return handleApiResponse {
            walletApiService.getCards()
        }
    }

    suspend fun addCard(card: NetworkCard): NetworkCard {
        return handleApiResponse {
            walletApiService.addCard(card)
        }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse {
            walletApiService.deleteCard(cardId)
        }
    }
}