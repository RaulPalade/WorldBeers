package com.raulp.worldbeers.data.network

import com.raulp.worldbeers.data.database.BeerDatabase
import com.raulp.worldbeers.data.models.Beer
import com.squareup.moshi.JsonClass

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

@JsonClass(generateAdapter = true)
data class NetworkBeerContainer(val beers: List<BeerDatabase>)

@JsonClass(generateAdapter = true)
data class NetworkBeer(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val description: String,
    val abv: String,
    val ibu: String,
    val firstBrewed: String,
    val foodPairing: List<String>,
    val brewerTips: String
)

fun NetworkBeerContainer.asDomainModel(): List<Beer> {
    return beers.map {
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

fun NetworkBeerContainer.asDatabaseModel(): Array<BeerDatabase> {
    return beers.map {
        BeerDatabase(
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
    }.toTypedArray()
}