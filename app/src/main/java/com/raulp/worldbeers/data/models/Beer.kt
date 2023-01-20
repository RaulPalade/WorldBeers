package com.raulp.worldbeers.data.models

import java.io.Serializable

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

data class Beer(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val description: String,
    val abv: String,
    val ibu: String,
    val firstBrewed: String,
    val foodPairing: List<String>,
    val brewerTips: String
) : Serializable