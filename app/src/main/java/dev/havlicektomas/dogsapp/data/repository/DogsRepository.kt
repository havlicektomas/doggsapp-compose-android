package dev.havlicektomas.dogsapp.data.repository

import dev.havlicektomas.dogsapp.data.local.DogsDatabase
import dev.havlicektomas.dogsapp.data.mapper.toDogsBreed
import dev.havlicektomas.dogsapp.data.mapper.toDogsBreedEntity
import dev.havlicektomas.dogsapp.data.remote.DogsApi
import dev.havlicektomas.dogsapp.domain.DogsBreed
import dev.havlicektomas.dogsapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import retrofit2.HttpException
import java.io.IOException

@Singleton
class DogsRepository @Inject constructor(
    db: DogsDatabase,
    private val api: DogsApi
) {

    private val dao = db.dogsBreedDao

    suspend fun getDogsBreeds(fetchFromRemote: Boolean): Flow<Resource<List<DogsBreed>>> {
        return flow {
            emit(Resource.Loading(true))

            val localDogsBreeds = dao.getDogsBreeds()
            emit(Resource.Success(localDogsBreeds.map { it.toDogsBreed() }))

            val isDbEmpty = localDogsBreeds.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteDogsBreeds = supervisorScope {
                try {
                    api.getDogBreeds().breeds.map {
                        async { DogsBreed(name = it, imageUrl = api.getRandomBreedImageFor(it).breedImageUrl) }
                    }.awaitAll()
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Couldn't load data"))
                    null
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Couldn't load data"))
                    null
                }
            }
            remoteDogsBreeds?.let { dogsBreeds ->
                dao.clearDogsBreeds()
                dao.insertDogsBreeds(dogsBreeds.map { it.toDogsBreedEntity() })
                emit(Resource.Success(dao.getDogsBreeds().map { it.toDogsBreed() }))
            }

            emit(Resource.Loading(false))
        }
    }

    suspend fun getDogsBreedByName(name: String): Flow<Resource<DogsBreed>> {
        return flow {
            val localDogsBreed = dao.getDogsBreedsByName(name)
            emit(Resource.Success(localDogsBreed.toDogsBreed()))
            return@flow
        }
    }
}