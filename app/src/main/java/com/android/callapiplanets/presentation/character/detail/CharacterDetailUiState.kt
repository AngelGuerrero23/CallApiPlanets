package com.android.callapiplanets.presentation.character.detail

import com.android.callapiplanets.domain.character.model.Character

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)
