package com.android.callapiplanets.di

import com.squareup.moshi.Moshi

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi
            .Builder()
            .add(kotlinJsonAdapterFactory())
            .build()
    }
}