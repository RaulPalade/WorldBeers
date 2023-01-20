package com.raulp.worldbeers.data.repository

import com.raulp.worldbeers.data.datasource.WorldBeerDataSource
import com.raulp.worldbeers.data.models.BeerResponse
import com.raulp.worldbeers.data.models.ResponseStatus
import retrofit2.Response

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class BeerRepository(private val worldBeerDataSource: WorldBeerDataSource) : IBeerRepository {

    override suspend fun getAllBeers(): ResponseStatus<Response<List<BeerResponse>>> {
        val response = worldBeerDataSource.getAllBeers()
        return if (response.isSuccessful) {
            ResponseStatus.Success(response)
        } else {
            ResponseStatus.Failure(response.message())
        }
    }
}