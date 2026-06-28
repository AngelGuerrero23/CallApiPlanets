package com.android.callapiplanets.presentation.planet.detail

import com.android.callapiplanets.domain.planet.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)
