package com.raulp.worldbeers.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raulp.worldbeers.data.models.Beer

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

@Entity(tableName = "beer")
data class BeerDatabase(
    @PrimaryKey
    val id: Long,
    @ColumnInfo("image_url")
    val imageUrl: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("abv")
    val abv: String,
    @ColumnInfo("ibu")
    val ibu: String,
    @ColumnInfo("first_brewed")
    val firstBrewed: String,
    @ColumnInfo("food_pairing")
    val foodPairing: List<String>,
    @ColumnInfo("brewer_tips")
    val brewerTips: String
)

fun List<BeerDatabase>.asDomainModel(): List<Beer> {
    return map {
        Beer(
            id = it.id,
            imageUrl = it.imageUrl,
            name = it.name,
            description = it.description,
            abv = it.abv,
            ibu = it.ibu,
            firstBrewed = it.firstBrewed,
            foodPairing = it.foodPairing,
            brewerTips = it.brewerTips
        )
    }
}