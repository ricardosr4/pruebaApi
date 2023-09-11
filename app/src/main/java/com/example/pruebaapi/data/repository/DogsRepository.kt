package com.example.pruebaapi.data.repository

import com.example.pruebaapi.data.remote.model.DogsResponse
import com.example.pruebaapi.data.source.DogsDataSource

class DogsRepository(private val dataSource: DogsDataSource) {
    suspend fun getDogs(user_name:String): List<DogsResponse> {
        return dataSource.getDogs(user_name)
    }
}