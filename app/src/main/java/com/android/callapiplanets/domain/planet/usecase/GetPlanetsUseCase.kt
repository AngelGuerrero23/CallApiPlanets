package com.android.callapiplanets.domain.planet.usecase

import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.planet.model.Planet
import com.android.callapiplanets.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null,
        isDestroyed: Boolean? = null,
    ): Flow<Resource<List<Planet>>> {
        return repository.getPlanets(page, limit, name, isDestroyed)
    }
}
