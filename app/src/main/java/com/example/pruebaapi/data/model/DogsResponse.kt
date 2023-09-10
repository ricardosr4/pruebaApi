package com.example.pruebaapi.data.model

import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var image: List<String>
)
