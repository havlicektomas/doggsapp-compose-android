package dev.havlicektomas.dogsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogsBreedEntity(
    val name: String,
    val imageUrl: String,
    @PrimaryKey val id: Int? = null
)
