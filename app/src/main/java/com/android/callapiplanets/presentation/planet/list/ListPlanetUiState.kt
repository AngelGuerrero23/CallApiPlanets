package com.android.callapiplanets.presentation.list

import com.android.callapiplanets.domain.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null,
)
