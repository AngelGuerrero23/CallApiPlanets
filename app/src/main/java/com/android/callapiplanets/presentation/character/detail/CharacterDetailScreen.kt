package com.android.callapiplanets.presentation.character.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.android.callapiplanets.domain.character.model.Character
import com.android.callapiplanets.domain.planet.model.Planet
import com.android.callapiplanets.presentation.character.list.ListCharacterUiState
import com.android.callapiplanets.presentation.planet.list.ListPlanetBodyScreen
import com.android.callapiplanets.presentation.planet.list.ListPlanetUiState
import com.android.callapiplanets.ui.theme.CallApiPlanetsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: DetailCharacterViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterDetailBodyScreen(
        state = state,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailBodyScreen(
    state: CharacterDetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Personaje") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        state.character?.let { character ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = character.name, style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Raza: ${character.race}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Género: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Ki: ${character.ki}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Máx Ki: ${character.maxKi}", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(12.dp))
                Text(text = character.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailBodyScreenPreview() {
    CallApiPlanetsTheme {
        CharacterDetailBodyScreen(
            state = CharacterDetailUiState(
                isLoading = false,
                character = Character(
                    id = 1,
                    name = "Goku",
                    race = "Saiyan",
                    gender = "Masculino",
                    ki = "60.000.000",
                    maxKi = "90.000.000.000",
                    description = "El saiyan criado en la Tierra, protector del universo y amante de los combates.",
                    image = ""
                )
            ),
            onBack = {}
        )
    }
}