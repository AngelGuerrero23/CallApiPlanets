package com.android.callapiplanets.presentation.list

sealed interface ListPlanetUiEvent{
    data class UpdateFilters(
        val planet: String,
        val isDestroyed: Boolean?
    ): ListPlanetUiEvent

    data object Search: ListPlanetUiEvent
}