package com.raulp.worldbeers.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class ListConverter {
    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}