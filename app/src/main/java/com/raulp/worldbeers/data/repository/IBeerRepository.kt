package com.raulp.worldbeers.data.repository

import com.raulp.worldbeers.data.models.BeerResponse
import com.raulp.worldbeers.data.models.ResponseResult
import retrofit2.Response

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

interface IBeerRepository {
    suspend fun getAllBeers(): ResponseResult<Response<List<BeerResponse>>>
}