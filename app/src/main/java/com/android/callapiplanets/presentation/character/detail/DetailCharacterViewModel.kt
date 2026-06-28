package com.android.callapiplanets.presentation.character.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.character.usecase.GetCharacterDetailUseCase
import com.android.callapiplanets.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailUiState())
    val state = _state.asStateFlow()

    init {
        try {
            val args = savedStateHandle.toRoute<Screen.CharacterDetailRoute>()
            loadCharacter(args.id)
        } catch (e: Exception) {
            _state.update { it.copy(error = "Error al obtener argumentos") }
        }
    }

    private fun loadCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, character = result.data) }
                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}
