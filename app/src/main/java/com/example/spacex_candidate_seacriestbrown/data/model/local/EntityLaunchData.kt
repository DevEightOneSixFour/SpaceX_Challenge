package com.example.spacex_candidate_seacriestbrown.data.model.local

import androidx.room.Entity
import com.example.spacex_candidate_seacriestbrown.data.model.remote.LaunchResponse

@Entity(tableName = "launches_table")
data class EntityLaunchData(
    val flightNumber: Int? = null,
    val missionName: String? = null,
    val missionId: List<String>? = null,
    val launchYear: String? = null,
    val rocketName: String? = null,
    val launchSiteName: String? = null,
    val launchDate: String? = null,
    val patchImage: String? = null,
    val articleLink: String? = null,
    val videoLink: String? = null,
    val details: String? = null,
    val flickerImages: List<String>? = null,
)

fun List<LaunchResponse>.toLaunchUIData(): List<EntityLaunchData> =
    this.map {
        EntityLaunchData(
            flightNumber = it.flightNumber,
            missionName = it.missionName,
            missionId = it.missionId ?: emptyList(),
            launchYear = it.launchYear,
            rocketName = it.rocket?.rocketName,
            launchSiteName = it.launchSite?.siteName,
            launchDate = it.launchDateUtc,
            patchImage = it.links?.missionPatch,
            articleLink = it.links?.articleLink,
            videoLink = it.links?.videoLink,
            details = it.details,
            flickerImages = it.links?.flickrImages ?: emptyList()
        )
    }

