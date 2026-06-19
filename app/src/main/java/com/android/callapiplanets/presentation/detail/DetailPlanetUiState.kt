package com.android.callapiplanets.presentation.detail

import com.android.callapiplanets.data.remote.dto.PlanetDto

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: PlanetDto? = null,
    val error: String? = null
)