package dev.havlicektomas.dogsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.havlicektomas.dogsapp.data.local.DogsDatabase
import dev.havlicektomas.dogsapp.data.remote.DogsApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogsApi(): DogsApi {
        return Retrofit.Builder()
            .baseUrl(DogsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDogsDatabase(app: Application): DogsDatabase {
        return Room.databaseBuilder(
            app,
            DogsDatabase::class.java,
            "dogsdb.db"
        ).build()
    }
}