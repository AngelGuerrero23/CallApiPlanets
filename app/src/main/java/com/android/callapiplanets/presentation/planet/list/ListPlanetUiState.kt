package com.android.callapiplanets.presentation.planet.list

import com.android.callapiplanets.data.planet.remote.dto.PlanetDto

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<PlanetDto> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null,
)