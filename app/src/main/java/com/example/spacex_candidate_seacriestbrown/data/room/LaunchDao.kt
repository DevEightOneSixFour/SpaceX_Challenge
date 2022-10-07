package com.example.spacex_candidate_seacriestbrown.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launches_table ORDER BY flightNumber ASC")
    fun getAllLaunches(): List<EntityLaunchData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: List<EntityLaunchData>)

    @Query("DELETE FROM launches_table")
    suspend fun deleteAll()
}