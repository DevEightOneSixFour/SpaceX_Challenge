package com.example.spacex_candidate_seacriestbrown.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.util.Converters

@Database(entities = [EntityLaunchData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LaunchDatabase: RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}