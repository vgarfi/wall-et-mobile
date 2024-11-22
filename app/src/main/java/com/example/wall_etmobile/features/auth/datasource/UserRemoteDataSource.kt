package com.example.wall_etmobile.features.auth.datasource

import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.core.datasource.RemoteDataSource
import com.example.wall_etmobile.features.auth.service.UserApiService
import com.example.wall_etmobile.network.model.NetworkCredentials
import com.example.wall_etmobile.network.model.NetworkUser
class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}