package com.android.callapiplanets.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.character.model.Character
import com.android.callapiplanets.domain.character.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharacterViewModel  @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListCharacterUiState())
    val state = _state.asStateFlow()


    init {
        loadCharacters()
    }

    fun onEvent(event: ListCharacterEvent) {
        when (event) {
            is ListCharacterEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        filterName = event.name,
                        filterGender = event.gender,
                        filterRace = event.race
                    )
                }
            }

            ListCharacterEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
         viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getCharactersUseCase(
                page = 1,
                limit = 50
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = it.characters.isEmpty()) }
                    }

                    is Resource.Success -> {
                        val charactersList = result.data as? List<Character> ?: emptyList()
                        val latestState = _state.value

                        val filteredList = charactersList.filter { character ->
                            val matchesName = latestState.filterName.isBlank() ||
                                    character.name.contains(latestState.filterName, ignoreCase = true)

                            val matchesGender = latestState.filterGender.isBlank() ||
                                    character.gender.contains(latestState.filterGender, ignoreCase = true)

                            val matchesRace = latestState.filterRace.isBlank() ||
                                    character.race.contains(latestState.filterRace, ignoreCase = true)

                            matchesName && matchesGender && matchesRace
                        }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                characters = filteredList
                            )
                        }
                    }
                    is Resource.Error ->
                        _state.update {
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