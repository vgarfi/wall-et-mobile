package com.example.wall_etmobile

import android.app.Application
import com.example.wall_etmobile.features.auth.datasource.UserRemoteDataSource
import com.example.wall_etmobile.core.config.RetrofitClient
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.features.auth.repository.UserRepository
import com.example.wall_etmobile.features.cards.datasource.CardRemoteDataSource
import com.example.wall_etmobile.features.cards.repository.CardRepository


class MyApplication : Application() {
    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val cardRemoteDataSource: CardRemoteDataSource
        get() = CardRemoteDataSource(RetrofitClient.getWalletApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val cardRepository: CardRepository
        get() = CardRepository(cardRemoteDataSource)
}