package com.example.wall_etmobile.network


import com.example.wall_etmobile.network.RemoteDataSource
import com.example.wall_etmobile.network.api.WalletApiService
import com.example.wall_etmobile.network.model.NetworkCard

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
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