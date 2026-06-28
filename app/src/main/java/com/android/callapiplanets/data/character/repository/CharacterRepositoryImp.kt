package com.android.callapiplanets.data.character.repository

import com.android.callapiplanets.data.planet.remote.DragonBallApi
import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.domain.character.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val api: DragonBallApi
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getCharacters(page, limit, name, gender, race)
            if (response.isSuccessful && response.body() != null) {
                val characters = response.body()!!.items.map { it.toDomain() }
                emit(Resource.Success(characters))
            } else {
                emit(Resource.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                emit(Resource.Error("Personaje no encontrado"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }
}
