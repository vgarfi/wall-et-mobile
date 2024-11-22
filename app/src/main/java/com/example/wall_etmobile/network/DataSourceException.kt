package com.example.wall_etmobile.network

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)