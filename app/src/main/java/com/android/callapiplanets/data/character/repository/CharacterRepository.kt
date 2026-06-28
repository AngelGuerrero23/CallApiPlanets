package com.android.callapiplanets.data.character.repository

import com.android.callapiplanets.data.character.remote.Resource
import com.android.callapiplanets.domain.character.model.Character


interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Resource<List<Character>>

    fun getCharacterDetail(id: Int): Resource<Character>
}