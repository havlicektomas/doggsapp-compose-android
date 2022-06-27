package dev.havlicektomas.dogsapp.data.remote

import dev.havlicektomas.dogsapp.data.remote.dto.DogsBreedImageDto
import dev.havlicektomas.dogsapp.data.remote.dto.DogsBreedsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {

    @GET("breeds/list")
    suspend fun getDogBreeds(): DogsBreedsDto

    @GET("breed/{breed}/images/random")
    suspend fun getRandomBreedImageFor(@Path("breed") breed: String): DogsBreedImageDto

    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }
}