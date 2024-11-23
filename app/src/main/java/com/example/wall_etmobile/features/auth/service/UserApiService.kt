package com.example.wall_etmobile.features.auth.service

import com.example.wall_etmobile.features.auth.model.CreateUserBody
import com.example.wall_etmobile.features.auth.model.LoginCredentials
import com.example.wall_etmobile.features.auth.model.JWTAuthToken
import com.example.wall_etmobile.features.auth.model.User
import com.example.wall_etmobile.features.auth.model.VerifyCodeBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("user/login")
    suspend fun login(@Body credentials: LoginCredentials): Response<JWTAuthToken>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<User>

    @POST("user")
    suspend fun register(@Body data: CreateUserBody): Response<User>

    @POST("user/verify")
    suspend fun verify(@Body code: VerifyCodeBody): Response<Unit>
}