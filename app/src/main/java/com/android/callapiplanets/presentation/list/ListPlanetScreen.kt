package com.android.callapiplanets.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.android.callapiplanets.data.remote.dto.PlanetDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanetScreen(
    viewModel: ListPlanetViewModel,
    onPlanetClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListPlanetBodyScreen(
        state= state,
        onEvent = viewModel::onEvent,
        onPlanetClick = onPlanetClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanetBodyScreen(
    state: ListPlanetUiState,
    onEvent: (ListPlanetUiEvent) -> Unit,
    onPlanetClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Planetas Dragon Ball")
                }
            )
        }
    ) {padding->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            FilterSection(
                name = state.nameFilter,
                onEvent = onEvent,
            )
            if(state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.planets){
                    planets->
                    PlanetItem(
                        planets = planets,
                        onPlanetClick= {onPlanetClick(planets.id)}
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    name: String,
    onEvent: (ListPlanetUiEvent) -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {onEvent(ListPlanetUiEvent.UpdateFilters(it, null))},
                label = {Text("Nombre: ")},
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {onEvent(ListPlanetUiEvent.Search)},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buscar")
            }
        }
    }
}

@Composable
fun PlanetItem(
    planets: PlanetDto,
    onPlanetClick: () -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPlanetClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = planets.image,
                contentDescription = planets.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = planets.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = if(planets.isDestroyed) "Destruido" else "Intacto",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if(planets.isDestroyed){
                        MaterialTheme.colorScheme.error
                    }
                    else{
                        MaterialTheme.colorScheme.primary
                    }
                )
            }
        }
    }
}