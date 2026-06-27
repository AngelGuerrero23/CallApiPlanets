package com.android.callapiplanets.domain.planet.usecase

import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.data.planet.remote.dto.PlanetDto
import com.android.callapiplanets.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int): Flow<Resource<PlanetDto>> {
        return repository.getPlanetDetail(id)
    }
}