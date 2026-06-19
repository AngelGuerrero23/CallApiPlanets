package com.android.callapiplanets.domain.usecase

import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: Int): Resource<PlanetDto>{
        return repository.getPlanetDetail(id)
    }
}