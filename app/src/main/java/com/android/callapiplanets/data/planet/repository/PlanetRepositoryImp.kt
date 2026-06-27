package com.android.callapiplanets.data.planet.repository

import com.android.callapiplanets.data.planet.remote.DragonBallApi
import com.android.callapiplanets.data.planet.remote.Resource
import com.android.callapiplanets.data.planet.remote.Resource.*
import com.android.callapiplanets.data.planet.remote.dto.PlanetDto
import com.android.callapiplanets.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImp @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<PlanetDto>>> = flow {

        emit(Loading())

        try {
            val response = api.getPlanets(page, limit, name, isDestroyed)

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.items
                emit(Success(data))
            } else {
                emit(Error("Error del servidor: ${response.message()}"))
            }
        } catch (e: Exception) {
            if (!name.isNullOrBlank()) {
                try {
                    val alternativeResponse =
                        api.getPlanets(page = 1, limit = 50, name = null, isDestroyed = null)

                    if (alternativeResponse.isSuccessful && alternativeResponse.body() != null) {
                        val filteredList = alternativeResponse.body()!!.items.filter {
                            it.name.contains(name, ignoreCase = true)
                        }
                        emit(Success(filteredList))
                    } else {
                        emit(Error("Error al filtrar localmente."))
                    }
                } catch (nestedException: Exception) {
                    emit(Error("Error en filtro: ${nestedException.localizedMessage}"))
                }
            } else {
                emit(Error("Error de conexion: ${e.localizedMessage}"))
            }
        }
    }

    override  fun getPlanetDetail(id: Int): Flow<Resource<PlanetDto>> = flow {

        emit(Loading())

        try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                emit(Success(response.body()!!))
            } else {
                emit(Error("Planeta no encontrado."))
            }
        } catch (e: Exception) {
            emit(Error("Error: ${e.message}"))
        }
    }
}