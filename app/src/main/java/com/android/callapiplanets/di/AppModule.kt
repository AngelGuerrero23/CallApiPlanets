package com.android.callapiplanets.di

import dagger.Module
import dagger.Provides
import com.android.callapiplanets.data.remote.DragonBallApi
import com.android.callapiplanets.data.repository.PlanetRepositoryImp
import com.android.callapiplanets.domain.repository.PlanetRepository
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides

    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi
            .Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi{
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: DragonBallApi): PlanetRepository{
        return PlanetRepositoryImp(api)
    }

}