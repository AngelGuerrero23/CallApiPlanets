package com.android.callapiplanets.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{

}

object PlanetListRoute

@Serializable
data class  PlanetDetailRoute(
    val id: Int
)
