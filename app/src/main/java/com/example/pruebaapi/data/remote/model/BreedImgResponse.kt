package com.example.pruebaapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class BreedImgResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var image: String
)
