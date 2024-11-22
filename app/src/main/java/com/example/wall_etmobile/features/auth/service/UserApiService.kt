package com.example.wall_etmobile.features.auth.service

import com.example.wall_etmobile.network.model.NetworkCredentials
import com.example.wall_etmobile.network.model.NetworkToken
import com.example.wall_etmobile.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>
}