package com.android.callapiplanets.data.repository

import com.android.callapiplanets.data.remote.DragonBallApi
import com.android.callapiplanets.data.remote.Resource
import com.android.callapiplanets.data.remote.Resource.*
import com.android.callapiplanets.data.remote.dto.PlanetDto
import com.android.callapiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class PlanetRepositoryImp @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository{
    override suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Resource<List<PlanetDto>> {
       return try {
           val response = api.getPlanets(page, limit, name, isDestroyed)

           if(response.isSuccessful && response.body()!=null){
               val data = response.body()!!.items
               Success(data)
           }else{
               Error("Error del servidor: ${response.message()}")
           }
       }catch (e: Exception){
           Error("Error de conexion: ${e.localizedMessage}")
       }
    }

    override suspend fun getPlanetDetail(id: Int): Resource<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Success(response.body()!!)
            } else {
                Error("Planeta no encontrado.")
            }
        } catch (e: Exception) {
            Error("Error: ${e.message}")
        }
    }
}