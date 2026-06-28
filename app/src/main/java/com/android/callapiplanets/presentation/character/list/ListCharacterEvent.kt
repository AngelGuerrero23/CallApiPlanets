package com.android.callapiplanets.presentation.character.list

sealed interface ListCharacterEvent {
    data class UpdateFilters(
        val name: String,
        val gender: String,
        val race: String
    ) : ListCharacterEvent

    data object Search : ListCharacterEvent
}