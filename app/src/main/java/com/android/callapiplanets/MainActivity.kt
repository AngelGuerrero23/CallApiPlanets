package com.android.callapiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.callapiplanets.presentation.detail.DetailPlanetScreen
import com.android.callapiplanets.presentation.detail.DetailPlanetViewModel
import com.android.callapiplanets.presentation.list.ListPlanetScreen
import com.android.callapiplanets.presentation.list.ListPlanetViewModel
import com.android.callapiplanets.ui.theme.CallApiPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@Serializable
object PlanetListRoute

@Serializable
data class  PlanetDetailRoute(
    val id: Int
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallApiPlanetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

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
            }
        }
    }
}
