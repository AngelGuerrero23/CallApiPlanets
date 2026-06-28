package com.android.callapiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.callapiplanets.presentation.navigation.AppNavHost
import com.android.callapiplanets.presentation.navigation.Screen
import com.android.callapiplanets.ui.theme.CallApiPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CallApiPlanetsTheme {
                val navController = rememberNavController()
                val items = listOf(
                    TopLevelRoute("Planetas", Screen.PlanetListRoute, Icons.Default.Place),
                    TopLevelRoute("Personajes", Screen.CharacterListRoute, Icons.Default.Person)
                )
                Scaffold(
                    bottomBar = {
                        NavigationBar() {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            items.forEach { item ->
                                NavigationBarItem(
                                    icon = { Icon(item.icono, contentDescription = item.nombre) },
                                    label = {Text(item.nombre)},
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute(item.ruta::class) } == true ,
                                    onClick = {
                                        navController.navigate(item.ruta){
                                            popUpTo (navController.graph.findStartDestination().id ){
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {innerPadding ->
                    AppNavHost(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}
data class TopLevelRoute<T : Any>(
    val nombre: String,
    val ruta : T,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)
