package com.android.callapiplanets.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.callapiplanets.presentation.detail.DetailPlanetScreen
import com.android.callapiplanets.presentation.detail.DetailPlanetViewModel
import com.android.callapiplanets.presentation.list.ListPlanetScreen
import com.android.callapiplanets.presentation.list.ListPlanetViewModel


@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = PlanetListRoute
    ) {

        composable<PlanetListRoute> {
            val listViewModel: ListPlanetViewModel = hiltViewModel()
            ListPlanetScreen(
                viewModel = listViewModel,
                onPlanetClick = { planetId ->
                    navController.navigate(PlanetDetailRoute(id = planetId))
                }
            )
        }

        composable<PlanetDetailRoute> {
            val detailViewModel: DetailPlanetViewModel = hiltViewModel()
            DetailPlanetScreen(
                viewModel = detailViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}