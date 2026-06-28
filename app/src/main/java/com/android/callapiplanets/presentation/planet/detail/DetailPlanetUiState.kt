package com.android.callapiplanets.presentation.planet.detail

import com.android.callapiplanets.data.planet.remote.dto.PlanetDto

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: PlanetDto? = null,
    val error: String? = null
)