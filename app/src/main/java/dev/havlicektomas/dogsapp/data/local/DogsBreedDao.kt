package dev.havlicektomas.dogsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogsBreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogsBreeds(dogsBreedEntities: List<DogsBreedEntity>)

    @Query("DELETE FROM dogsbreedentity")
    suspend fun clearDogsBreeds()

    @Query(
        """
            SELECT *
            FROM dogsbreedentity
        """
    )
    suspend fun getDogsBreeds(): List<DogsBreedEntity>

    @Query(
        """
            SELECT *
            FROM dogsbreedentity
            WHERE :name == name
        """
    )
    suspend fun getDogsBreedsByName(name: String): DogsBreedEntity
}