package com.example.wall_etmobile.features.auth.repository

import com.example.wall_etmobile.features.auth.datasource.UserRemoteDataSource
import com.example.wall_etmobile.features.auth.model.User
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun getCurrentUser(refresh: Boolean) : User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }

    suspend fun register(firstName: String, lastName: String, email: String, password: String) {
        remoteDataSource.register(firstName, lastName, email, password)
    }
}