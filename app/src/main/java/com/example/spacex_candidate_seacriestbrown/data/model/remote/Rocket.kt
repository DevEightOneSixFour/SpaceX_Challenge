package com.example.spacex_candidate_seacriestbrown.data.model.remote

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("rocket_id") val rocketId: String? = null,
    @SerializedName("rocket_name") val rocketName: String? = null,
    @SerializedName("rocket_type") val rocketType: String? = null,
    @SerializedName("first_stage") val firstStage: FirstStage? = null,
    @SerializedName("second_stage") val secondStage: SecondStage? = null,
    @SerializedName("fairings") val fairings: Fairings? = null
)

data class FirstStage(
    @SerializedName("cores") val cores: List<Cores> = emptyList()
)

data class SecondStage(
    @SerializedName("block") val block: Int? = null,
    @SerializedName("payloads") val payloads: List<Payloads> = emptyList()
)

data class Fairings(
    @SerializedName("reused") val reused: Boolean? = null,
    @SerializedName("recovery_attempt") val recoveryAttempt: Boolean? = null,
    @SerializedName("recovered") val recovered: Boolean? = null,
    @SerializedName("ship") val ship: String? = null
)

data class Cores(
    @SerializedName("core_serial") val coreSerial: String? = null,
    @SerializedName("flight") val flight: Int? = null,
    @SerializedName("block") val block: String? = null,
    @SerializedName("gridfins") val gridfins: Boolean? = null,
    @SerializedName("legs") val legs: Boolean? = null,
    @SerializedName("reused") val reused: Boolean? = null,
    @SerializedName("land_success") val landSuccess: String? = null,
    @SerializedName("landing_intent") val landingIntent: Boolean? = null,
    @SerializedName("landing_type") val landingType: String? = null,
    @SerializedName("landing_vehicle") val landingVehicle: String? = null
)

data class Payloads(
    @SerializedName("payload_id") val payloadId: String? = null,
    @SerializedName("norad_id") val noradId: ArrayList<String> = arrayListOf(),
    @SerializedName("reused") val reused: Boolean? = null,
    @SerializedName("customers") val customers: ArrayList<String> = arrayListOf(),
    @SerializedName("nationality") val nationality: String? = null,
    @SerializedName("manufacturer") val manufacturer: String? = null,
    @SerializedName("payload_type") val payloadType: String? = null,
    @SerializedName("payload_mass_kg") val payloadMassKg: Double? = null,
    @SerializedName("payload_mass_lbs") val payloadMassLbs: Double? = null,
    @SerializedName("orbit") val orbit: String? = null,
    @SerializedName("orbit_params") val orbitParams: OrbitParams? = null
)

data class OrbitParams(
    @SerializedName("reference_system") val referenceSystem: String? = null,
    @SerializedName("regime") val regime: String? = null,
    @SerializedName("longitude") val longitude: String? = null,
    @SerializedName("semi_major_axis_km") val semiMajorAxisKm: String? = null,
    @SerializedName("eccentricity") val eccentricity: String? = null,
    @SerializedName("periapsis_km") val periapsisKm: Double? = null,
    @SerializedName("apoapsis_km") val apoapsisKm: Double? = null,
    @SerializedName("inclination_deg") val inclinationDeg: Double? = null,
    @SerializedName("period_min") val periodMin: String? = null,
    @SerializedName("lifespan_years") val lifespanYears: String? = null,
    @SerializedName("epoch") val epoch: String? = null,
    @SerializedName("mean_motion") val meanMotion: String? = null,
    @SerializedName("raan") val raan: String? = null,
    @SerializedName("arg_of_pericenter") val argOfPericenter: String? = null,
    @SerializedName("mean_anomaly") val meanAnomaly: String? = null
)