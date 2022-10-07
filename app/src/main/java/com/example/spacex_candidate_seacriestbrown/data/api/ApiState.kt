package com.example.spacex_candidate_seacriestbrown.data.api

import com.example.spacex_candidate_seacriestbrown.data.model.remote.LaunchResponse

sealed class ApiState {
    class Error(val exception: Exception): ApiState()
    class Success(val response: List<LaunchResponse>): ApiState()
}
