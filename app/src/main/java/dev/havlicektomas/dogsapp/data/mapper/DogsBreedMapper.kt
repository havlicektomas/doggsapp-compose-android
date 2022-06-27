package dev.havlicektomas.dogsapp.data.mapper

import dev.havlicektomas.dogsapp.data.local.DogsBreedEntity
import dev.havlicektomas.dogsapp.domain.DogsBreed

fun DogsBreedEntity.toDogsBreed(): DogsBreed {
    return DogsBreed(
        name = name,
        imageUrl = imageUrl
    )
}

fun DogsBreed.toDogsBreedEntity(): DogsBreedEntity {
    return DogsBreedEntity(
        name = name,
        imageUrl = imageUrl
    )
}