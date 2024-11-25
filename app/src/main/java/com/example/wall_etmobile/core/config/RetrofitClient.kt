package com.example.wall_etmobile.core.config

import android.content.Context
import com.example.wall_etmobile.features.auth.service.UserApiService
import com.example.wall_etmobile.features.auth.service.UserWalletApiService
import com.example.wall_etmobile.features.cards.service.CardApiService
import com.example.wall_etmobile.features.transactions.service.TransactionApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    // No usar localhost o la IP 127.0.0.1 porque es la interfaz de loopback
    // del emulador. La forma de salir del emulador para acceder al localhost
    // de host del mismo es usando la IP 10.0.2.2.
    private const val BASE_URL = "http://192.168.0.59:8080/api/"

    @Volatile
    private var instance: Retrofit? = null

    private fun getInstance(context: Context): Retrofit =
        instance ?: synchronized(this) {
            instance ?: buildRetrofit(context).also { instance = it }
        }

    private fun buildRetrofit(context: Context): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    fun getUserApiService(context: Context): UserApiService {
        return getInstance(context).create(UserApiService::class.java)
    }

    fun getCardApiService(context: Context): CardApiService {
        return getInstance(context).create(CardApiService::class.java)
    }

    fun getUserWalletApiService(context: Context): UserWalletApiService {
        return getInstance(context).create(UserWalletApiService::class.java)
    }

    fun getTransactionApiService(context: Context): TransactionApiService {
        return getInstance(context).create(TransactionApiService::class.java)
    }
}