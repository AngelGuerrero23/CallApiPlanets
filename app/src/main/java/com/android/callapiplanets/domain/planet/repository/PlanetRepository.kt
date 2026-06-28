package com.android.callapiplanets.domain.planet.repository

import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.planet.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}
