package com.android.callapiplanets.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Screen {

    @Serializable
    data object PlanetListRoute : Screen()

    @Serializable
    data class PlanetDetailRoute(val id: Int): Screen()


    @Serializable
    data object CharacterListRoute : Screen()

    @Serializable
    data class CharacterDetailRoute(val id: Int) : Screen()
}