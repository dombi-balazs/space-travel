package com.space.travel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//
interface SolaireApi {
    @GET("/rest/bodies/{planetName}")
    suspend fun getPlanetInfo(
        @Path("planetName") planetName: String
    ): Response<PlanetData>
}