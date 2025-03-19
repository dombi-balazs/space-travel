package com.space.travel

import com.google.gson.annotations.SerializedName

data class PlanetData(
    @SerializedName("englishName")  val englishName: String,
    val moons: List<Moons>,
    val name: String,
    val gravity: Double,
    val density: Double,
    val meanRadius: Double,
    val avgTemp: Int,
    val discoveredBy: String,
    val bodyType: String
    )
data class Moons(
    val name: String, // moon from json
)
