package com.example.spacex_candidate_seacriestbrown.domain.repository

import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.data.room.LaunchDatabase
import javax.inject.Inject

interface LaunchLocalRepository {
    suspend fun getLocalLaunchData(): List<EntityLaunchData>
    suspend fun insertLocalLaunchData(data: List<EntityLaunchData>)
    suspend fun deleteAll()
}

class LaunchLocalRepositoryImpl @Inject constructor(private val database: LaunchDatabase)
    : LaunchLocalRepository {
    override suspend fun getLocalLaunchData(): List<EntityLaunchData> {
        return database.launchDao().getAllLaunches()
    }

    override suspend fun insertLocalLaunchData(data: List<EntityLaunchData>) {
        return database.launchDao().insert(data)
    }

    override suspend fun deleteAll() {
        database.launchDao().deleteAll()
    }
}