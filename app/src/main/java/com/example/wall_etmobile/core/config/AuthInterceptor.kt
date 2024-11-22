package com.example.wall_etmobile.core.config

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

//        sessionManager.loadAuthToken()?.let {
//            requestBuilder.addHeader("Authorization", "Bearer $it")
//        }

        // TODO eliminar este hardcodeo
        requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTczMjI0MTg1ODc0MCwiZXhwIjoxNzMyMjQ0NDUwNzQwfQ.EeXZIO4ccHlbdZtRDXSpkVdv7dUXEs0w6jc-1_r4KHM")
        return chain.proceed(requestBuilder.build())
    }
}