package dev.havlicektomas.dogsapp.data.remote.dto

import com.squareup.moshi.Json

data class DogsBreedImageDto(
    @field:Json(name = "message") val breedImageUrl: String
)
