package com.android.callapiplanets.domain.usecase

import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(
        page: Int=1,
        limit: Int=10,
        name: String? =null,
        isDestroyed: Boolean? = null,
    ): Resource<List<PlanetDto>> {
        return repository.getPlanets(page, limit, name, isDestroyed)
    }
}