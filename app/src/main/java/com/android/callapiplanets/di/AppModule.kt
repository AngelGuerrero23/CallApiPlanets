package com.android.callapiplanets.di

import com.android.callapiplanets.data.character.repository.CharacterRepository
import com.android.callapiplanets.data.character.repository.CharacterRepositoryImp
import dagger.Module
import dagger.Provides
import com.android.callapiplanets.data.planet.remote.DragonBallApi
import com.android.callapiplanets.data.planet.repository.PlanetRepositoryImp
import com.android.callapiplanets.domain.planet.repository.PlanetRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi {
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlanetRepository(api: DragonBallApi): PlanetRepository {
        return PlanetRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: DragonBallApi): CharacterRepository {
        return CharacterRepositoryImp(api)
    }
}
