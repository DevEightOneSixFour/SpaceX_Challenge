package com.example.spacex_candidate_seacriestbrown.data.model.remote

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("mission_patch") val missionPatch: String? = null,
    @SerializedName("mission_patch_small") val missionPatchSmall: String? = null,
    @SerializedName("reddit_campaign") val redditCampaign: String? = null,
    @SerializedName("reddit_launch") val redditLaunch: String? = null,
    @SerializedName("reddit_recovery") val redditRecovery: String? = null,
    @SerializedName("reddit_media") val redditMedia: String? = null,
    @SerializedName("presskit") val presskit: String? = null,
    @SerializedName("article_link") val articleLink: String? = null,
    @SerializedName("wikipedia") val wikipedia: String? = null,
    @SerializedName("video_link") val videoLink: String? = null,
    @SerializedName("youtube_id") val youtubeId: String? = null,
    @SerializedName("flickr_images") val flickrImages: List<String>? = emptyList()
)
