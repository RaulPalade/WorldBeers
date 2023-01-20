package com.raulp.worldbeers.data.datasource

import com.raulp.worldbeers.data.network.NetworkBeerContainer
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

interface WorldBeerDataSource {
    @GET("v2/beers")
    suspend fun getAllBeers(): Response<List<NetworkBeerContainer>>
}