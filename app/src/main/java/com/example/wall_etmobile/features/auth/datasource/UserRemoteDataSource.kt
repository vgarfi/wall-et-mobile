package com.example.wall_etmobile.features.auth.datasource

import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.core.datasource.RemoteDataSource
import com.example.wall_etmobile.features.auth.model.CreateUserBody
import com.example.wall_etmobile.features.auth.service.UserApiService
import com.example.wall_etmobile.features.auth.model.LoginCredentials
import com.example.wall_etmobile.features.auth.model.User

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(LoginCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): User {
        return handleApiResponse { userApiService.getCurrentUser() }
    }

    suspend fun register(firstName: String, lastName: String, email: String, password: String) {
        val body = CreateUserBody(firstName, lastName, email, password, "2003-03-04")
        handleApiResponse { userApiService.register(body) }
    }
}