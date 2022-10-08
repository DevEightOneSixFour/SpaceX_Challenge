package com.example.spacex_candidate_seacriestbrown.domain.repository

import android.util.Log
import com.example.spacex_candidate_seacriestbrown.data.api.ApiService
import com.example.spacex_candidate_seacriestbrown.data.api.ApiState
import com.example.spacex_candidate_seacriestbrown.util.TAG
import javax.inject.Inject

interface LaunchRemoteRepository {
    suspend fun fetchLaunches(): ApiState
}

class LaunchRemoteRepositoryImpl @Inject constructor(private val service: ApiService)
    : LaunchRemoteRepository {
    override suspend fun fetchLaunches(): ApiState {
        val apiState: ApiState = try {
            val response = service.fetchLaunches()
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiState.Success(it)
                } ?: throw Exception("Empty response")
            } else
                throw Exception("Failed network call")
        } catch (e: Exception) {
            Log.d(TAG, "in Repo: Error ${e.message}")
            ApiState.Error(e)
        }

        return apiState
    }
}
