package com.example.spacex_candidate_seacriestbrown.di.module

import android.content.Context
import androidx.room.Room
import com.example.spacex_candidate_seacriestbrown.data.room.LaunchDao
import com.example.spacex_candidate_seacriestbrown.data.room.LaunchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB_NAME = "launches_database"

    @Provides
    fun provideLaunchesDatabase(@ApplicationContext context: Context): LaunchDatabase =
        Room.databaseBuilder(
            context,
            LaunchDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideLaunchDao(db: LaunchDatabase): LaunchDao = db.launchDao()
}