package com.example.spacex_candidate_seacriestbrown.domain.usecase

import android.content.Context
import com.example.spacex_candidate_seacriestbrown.data.api.ApiState
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.data.model.local.toLaunchUIData
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchLocalRepository
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchRemoteRepository
import com.example.spacex_candidate_seacriestbrown.util.NetworkUtil
import javax.inject.Inject

class LaunchApiUseCase @Inject constructor(
    private val remoteRepository: LaunchRemoteRepository,
    private val localRepository: LaunchLocalRepository
) {
    /**
     * First check if there is network available.
     * If so, make the call to retrieve data.
     * If not or there is an error, pull from the database
     */
    suspend fun fetchLaunches(context: Context): List<EntityLaunchData> {
        if (NetworkUtil.isNetworkConnected(context)) {
            return when (val state = remoteRepository.fetchLaunches()) {
                is ApiState.Success -> {
                    val cachingData = state.response.toLaunchUIData()
                    localRepository.insertLocalLaunchData(cachingData)
                    cachingData
                }
                is ApiState.Error -> {
                    localRepository.getLocalLaunchData()
                }
            }
        }

       return localRepository.getLocalLaunchData()
    }

    suspend fun deleteAll() {
        localRepository.deleteAll()
    }
}