package com.android.callapiplanets.presentation.character.list

import com.android.callapiplanets.domain.character.model.Character

data class ListCharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = "",

)