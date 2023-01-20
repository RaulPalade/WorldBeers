package com.raulp.worldbeers.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.raulp.worldbeers.data.database.BeerRoomDatabase
import com.raulp.worldbeers.data.database.asDomainModel
import com.raulp.worldbeers.data.datasource.WorldBeerDataSource
import com.raulp.worldbeers.data.models.Beer
import com.raulp.worldbeers.data.models.ResponseResult
import com.raulp.worldbeers.data.network.NetworkBeerContainer
import retrofit2.Response

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class BeerRepository(
    private val worldBeerDataSource: WorldBeerDataSource,
    private val database: BeerRoomDatabase
) : IBeerRepository {
    val beers: LiveData<List<Beer>> = Transformations.map(database.beerDao().getAllBeers()) {
        it.asDomainModel()
    }


    override suspend fun getAllBeers(): ResponseResult<Response<List<NetworkBeerContainer>>> {
        val response = worldBeerDataSource.getAllBeers()
        return if (response.isSuccessful) {

            ResponseResult.Success(response)
        } else {
            ResponseResult.Failure(response.message())
        }
    }
}