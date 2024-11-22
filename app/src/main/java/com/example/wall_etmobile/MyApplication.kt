package com.example.wall_etmobile

import android.app.Application
import com.example.wall_etmobile.features.auth.datasource.UserRemoteDataSource
import com.example.wall_etmobile.network.WalletRemoteDataSource
import com.example.wall_etmobile.core.config.RetrofitClient
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.repository.UserRepository
import com.example.wall_etmobile.repository.WalletRepository


class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)
}