package com.creative.apps.kento.domain.remote.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("youtube_id") val youtubeId : String? = null,
    val url : String? = null,
    @SerialName("embed_url") val embedUrl : String?,
    val images : Image? = null,
)
