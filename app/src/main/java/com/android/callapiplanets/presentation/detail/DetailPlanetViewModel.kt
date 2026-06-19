package com.android.callapiplanets.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.android.callapiplanets.PlanetDetailRoute
import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.domain.usecase.GetPlanetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    savedState: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailPlanetUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedState.toRoute<PlanetDetailRoute>()
        loadPlanet(args.id)
    }

    private fun loadPlanet(id: Int){
        viewModelScope.launch {
            _state.update{it.copy(isLoading = true)}
            when(val result = getPlanetDetailUseCase(id)){
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, planet = result.data) }
                    is Resource.Error -> _state.update { it.copy(
                        isLoading = false,
                        error = result.message
                    ) }
                }
            }
        }
    }
