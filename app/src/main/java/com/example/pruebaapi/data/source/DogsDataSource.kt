package com.example.pruebaapi.data.source

import com.example.pruebaapi.data.remote.model.DogsResponse
import com.example.pruebaapi.data.remote.network.APIService

class DogsDataSource(private val apiService: APIService) {
    suspend fun getDogs(user_name:String): List<DogsResponse>{
        return apiService.getDogsByBreeds(user_name)
    }

}