package com.acronym.app.network.api

import com.acronym.app.model.AcronymResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    @GET("/software/acromine/dictionary.py")
    suspend fun getAbbreviation(@Query("sf") shortName: String) : Response<List<AcronymResponse>?>
}