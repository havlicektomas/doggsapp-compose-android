package dev.havlicektomas.dogsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DogsBreedEntity::class],
    version = 1
)
abstract class DogsDatabase: RoomDatabase() {

    abstract val dogsBreedDao: DogsBreedDao
}