package com.android.callapiplanets.presentation.planet.list

import com.android.callapiplanets.domain.planet.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null,
)
