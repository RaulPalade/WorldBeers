package com.raulp.worldbeers.data.repository

import com.raulp.worldbeers.data.models.ResponseResult
import com.raulp.worldbeers.data.network.NetworkBeerContainer
import retrofit2.Response

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

interface IBeerRepository {
    suspend fun getAllBeers(): ResponseResult<Response<List<NetworkBeerContainer>>>
}