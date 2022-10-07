package com.example.spacex_candidate_seacriestbrown.data.api

import com.example.spacex_candidate_seacriestbrown.data.model.remote.LaunchResponse
import com.example.spacex_candidate_seacriestbrown.util.ApiConstants.LAUNCHES
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(LAUNCHES)
    suspend fun fetchLaunches(): Response<List<LaunchResponse>>
}