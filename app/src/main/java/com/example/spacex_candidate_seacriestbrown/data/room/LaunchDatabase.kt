package com.example.spacex_candidate_seacriestbrown.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData

@Database(entities = [EntityLaunchData::class], version = 1, exportSchema = false)
abstract class LaunchDatabase: RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}