package com.raulp.worldbeers.data.repository

import com.raulp.worldbeers.data.datasource.WorldBeerDataSource
import com.raulp.worldbeers.data.models.BeerResponse
import com.raulp.worldbeers.data.models.ResponseResult
import retrofit2.Response

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class BeerRepository(private val worldBeerDataSource: WorldBeerDataSource) : IBeerRepository {

    override suspend fun getAllBeers(): ResponseResult<Response<List<BeerResponse>>> {
        val response = worldBeerDataSource.getAllBeers()
        return if (response.isSuccessful) {
            ResponseResult.Success(response)
        } else {
            ResponseResult.Failure(response.message())
        }
    }
}