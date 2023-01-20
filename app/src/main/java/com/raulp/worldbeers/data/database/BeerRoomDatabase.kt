package com.raulp.worldbeers.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

@Database(entities = [BeerDatabase::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class BeerRoomDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

    companion object {
        @Volatile
        private var INSTANCE: BeerRoomDatabase? = null

        fun getDatabase(context: Context): BeerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BeerRoomDatabase::class.java,
                    "beer_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}