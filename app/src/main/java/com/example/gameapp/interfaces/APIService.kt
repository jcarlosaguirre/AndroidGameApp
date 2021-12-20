package com.example.gameapp.interfaces

import com.example.gameapp.classes.AdventuresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getAdventuresImages(@Url url: String): Response<AdventuresResponse>
}