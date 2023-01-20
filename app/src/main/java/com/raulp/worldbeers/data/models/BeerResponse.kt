package com.raulp.worldbeers.data.models

import com.google.gson.annotations.SerializedName

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

data class BeerResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("abv")
    val abv: String,
    @SerializedName("ibu")
    val ibu: String,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("brewer_tips")
    val brewerTips: String
)

fun BeerResponse.toBeer(): Beer {
    return Beer(
        id ?: 0,
        imageUrl.orEmpty(),
        name.orEmpty(),
        description.orEmpty(),
        abv ?: "N/A",
        ibu ?: "N/A",
        firstBrewed.orEmpty(),
        foodPairing.orEmpty(),
        brewerTips.orEmpty()
    )
}