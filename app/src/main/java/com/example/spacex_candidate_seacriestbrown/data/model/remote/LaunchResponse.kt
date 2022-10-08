package com.example.spacex_candidate_seacriestbrown.data.model.remote

import com.google.gson.annotations.SerializedName

data class LaunchResponse(
    @SerializedName("flight_number") val flightNumber: Int? = null,
    @SerializedName("mission_name") val missionName: String? = null,
    @SerializedName("mission_id") val missionId: List<String>? = null,
    @SerializedName("upcoming") val upcoming: Boolean? = null,
    @SerializedName("launch_year") val launchYear: String? = null,
    @SerializedName("launch_date_unix") val launchDateUnix: Int? = null,
    @SerializedName("launch_date_utc") val launchDateUtc: String? = null,
    @SerializedName("launch_date_local") val launchDateLocal: String? = null,
    @SerializedName("is_tentative") val isTentative: Boolean? = null,
    @SerializedName("tentative_max_precision") val tentativeMaxPrecision: String? = null,
    @SerializedName("tbd") val tbd: Boolean? = null,
    @SerializedName("launch_window") val launchWindow: Int? = null,
    @SerializedName("rocket") val rocket: Rocket? = null,
    @SerializedName("ships") val ships: List<String> = emptyList(),
    @SerializedName("telemetry") val telemetry: Telemetry? = null,
    @SerializedName("launch_site") val launchSite: LaunchSite? = null,
    @SerializedName("launch_success") val launchSuccess: Boolean? = null,
    @SerializedName("launch_failure_details") val launchFailureDetails: LaunchFailureDetails? = null,
    @SerializedName("links") val links: Links? = null,
    @SerializedName("details") val details: String? = null,
    @SerializedName("static_fire_date_utc") val staticFireDateUtc: String? = null,
    @SerializedName("static_fire_date_unix") val staticFireDateUnix: Int? = null,
    @SerializedName("timeline") val timeline: Timeline? = null,
    @SerializedName("crew") val crew: List<Any>? = null
)

data class Telemetry(
    @SerializedName("flight_club") val flightClub: String? = null
)

data class LaunchSite(
    @SerializedName("site_id") val siteId: String? = null,
    @SerializedName("site_name") val siteName: String? = null,
    @SerializedName("site_name_long") val siteNameLong: String? = null
)

data class LaunchFailureDetails(
    @SerializedName("time") val time: Int? = null,
    @SerializedName("altitude") val altitude: String? = null,
    @SerializedName("reason") val reason: String? = null
)

data class Timeline(
    @SerializedName("webcast_liftoff") val webcastLiftoff: Int? = null
)