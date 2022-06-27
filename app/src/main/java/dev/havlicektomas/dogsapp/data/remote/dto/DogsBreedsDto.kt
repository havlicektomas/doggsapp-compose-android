package dev.havlicektomas.dogsapp.data.remote.dto

import com.squareup.moshi.Json

data class DogsBreedsDto(
    @field:Json(name = "message") val breeds: List<String>
)
