package com.example.wall_etmobile.core.datasource

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)