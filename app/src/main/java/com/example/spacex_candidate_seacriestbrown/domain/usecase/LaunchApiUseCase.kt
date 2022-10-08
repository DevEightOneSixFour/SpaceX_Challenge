package com.example.spacex_candidate_seacriestbrown.domain.usecase

import android.util.Log
import com.example.spacex_candidate_seacriestbrown.data.api.ApiState
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.data.model.local.toLaunchUIData
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchLocalRepository
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchRemoteRepository
import com.example.spacex_candidate_seacriestbrown.util.TAG
import javax.inject.Inject

class LaunchApiUseCase @Inject constructor(
    private val remoteRepository: LaunchRemoteRepository,
    private val localRepository: LaunchLocalRepository
) {
    suspend fun fetchLaunches(): List<EntityLaunchData> {
        return when(val state = remoteRepository.fetchLaunches()) {
            is ApiState.Success -> {
                Log.d(TAG, "fetchLaunches: Successful")
                val cachingData = state.response.toLaunchUIData()
                Log.d(TAG, "caching: $cachingData")
                localRepository.insertLocalLaunchData(cachingData)
                cachingData
            }
            is ApiState.Error -> {
                localRepository.getLocalLaunchData()
            }
        }
    }
}