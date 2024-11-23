package com.example.wall_etmobile.features.cards.service

import com.example.wall_etmobile.features.cards.model.NetworkCreditCard
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardApiService {

    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCreditCard>>

    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkCreditCard): Response<NetworkCreditCard>

    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>
}