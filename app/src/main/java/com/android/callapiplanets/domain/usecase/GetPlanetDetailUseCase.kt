package com.android.callapiplanets.domain.usecase

import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.model.Planet
import com.android.callapiplanets.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Planet>>{
        return repository.getPlanetDetail(id)
    }
}