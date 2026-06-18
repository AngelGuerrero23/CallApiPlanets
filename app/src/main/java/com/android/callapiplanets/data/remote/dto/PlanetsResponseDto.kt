package com.android.callapiplanets.data.remote.dto

import com.android.callapiplanets.domain.model.Planet

data class PlanetsResponseDto(
    val items: List<PlanetDto>
)

data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
){
    fun toDomain() = Planet(
        id,
        name,
        isDestroyed,
        description,
        image
    )
}
