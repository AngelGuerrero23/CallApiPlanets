package com.android.callapiplanets.data.character.remote.dto

import com.android.callapiplanets.domain.character.model.Character
import com.squareup.moshi.JsonClass


data class CharacterResponseDto(
    val items: List<CharacterDto>
)


data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String,
) {
    fun toDomain() = Character(
        id, name, ki, race, gender, description, image, maxKi,
    )
}
