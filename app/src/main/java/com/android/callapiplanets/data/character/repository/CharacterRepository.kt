package com.android.callapiplanets.data.character.repository

import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}
