package com.example.pruebaapi.domain.useCase

import com.example.pruebaapi.data.remote.model.DogsResponse
import com.example.pruebaapi.data.repository.DogsRepository

class DogsUseCase(private val repository: DogsRepository){
    suspend fun getDogs(params: String): List<DogsResponse>? {
        return repository.getDogs(params)


    }
}