package com.android.callapiplanets.presentation.detail

import com.android.callapiplanets.domain.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)
