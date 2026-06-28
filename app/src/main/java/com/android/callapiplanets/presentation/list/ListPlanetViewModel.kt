package com.android.callapiplanets.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.domain.usecase.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ListPlanetUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanet()
    }

    fun onEvent(event: ListPlanetUiEvent) {
        when (event) {
            is ListPlanetUiEvent.UpdateFilters -> _state.update {
                it.copy(
                    nameFilter = event.planet,
                    isDestroyedFilter = event.isDestroyed
                )
            }

            ListPlanetUiEvent.Search -> loadPlanet()
        }
    }

    fun loadPlanet() {
        viewModelScope.launch {
            val current = _state.value
            getPlanetsUseCase(
                page = 1,
                limit = 20,
                name = current.nameFilter.trim().takeIf { it.isNotBlank() },
                isDestroyed = current.isDestroyedFilter
            ).collect { result ->
                when (result) {
                    is Resource.Success ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data ?: emptyList()
                            )
                        }

                    is Resource.Error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = emptyList(),
                                error = result.message
                            )
                        }

                    is Resource.Loading ->
                        _state.update {
                            it.copy(
                                isLoading = true,
                            )
                        }
                }
            }
        }
    }
}
