package com.android.callapiplanets.domain.planet.usecase

import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.planet.model.Planet
import com.android.callapiplanets.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Planet>> {
        return repository.getPlanetDetail(id)
    }
}
