package com.android.callapiplanets.domain.repository

import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<PlanetDto>>>

    suspend fun getPlanetDetail(id: Int): Flow<Resource<PlanetDto>>
}