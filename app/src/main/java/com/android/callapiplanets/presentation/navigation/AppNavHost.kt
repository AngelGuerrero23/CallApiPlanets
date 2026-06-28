package com.android.callapiplanets.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.callapiplanets.presentation.character.detail.CharacterDetailScreen
import com.android.callapiplanets.presentation.character.list.ListScreenCharacter
import com.android.callapiplanets.presentation.planet.detail.DetailPlanetScreen
import com.android.callapiplanets.presentation.planet.list.ListPlanetScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.PlanetListRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Screen.PlanetListRoute> {
            ListPlanetScreen(
                viewModel = hiltViewModel(),
                onPlanetClick = { planetId ->
                    navController.navigate(Screen.PlanetDetailRoute(id = planetId))
                }
            )
        }

        composable<Screen.PlanetDetailRoute> {
            DetailPlanetScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.CharacterListRoute> {
            ListScreenCharacter(
                onCharacterClick = {characterId->
                    navController.navigate(Screen.CharacterDetailRoute(id = characterId))
                }
            )
        }

        composable<Screen.CharacterDetailRoute> {
            CharacterDetailScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
