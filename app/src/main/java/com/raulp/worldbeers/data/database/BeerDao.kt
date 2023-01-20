package com.raulp.worldbeers.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBeers(vararg beers: List<Array<BeerDatabase>>)

    @Query("select * from beer")
    fun getAllBeers(): LiveData<List<BeerDatabase>>
}