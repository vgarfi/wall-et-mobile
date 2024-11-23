package com.example.wall_etmobile.features.auth.service

import com.example.wall_etmobile.features.auth.model.CreateUserBody
import com.example.wall_etmobile.features.auth.model.LoginCredentials
import com.example.wall_etmobile.features.auth.model.JWTAuthToken
import com.example.wall_etmobile.features.auth.model.User
import com.example.wall_etmobile.features.auth.model.UserWallet
import com.example.wall_etmobile.features.auth.model.VerifyCodeBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserWalletApiService {
    @GET("wallet/details")
    suspend fun getUserWallet(): Response<UserWallet>
}
