package com.android.callapiplanets.domain.repository

import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Resource<List<PlanetDto>>

    suspend fun getPlanetDetail(id: Int): Resource<PlanetDto>
}