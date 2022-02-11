package com.creative.apps.kento.domain.remote.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("image_url") val url : String? = null,
    @SerialName("small_image_url") val smallImage : String? = null,
    @SerialName("medium_image_url") val mediumImage : String? = null,
    @SerialName("large_image_url") val largeImage : String? = null,
    @SerialName("maximum_image_url") val maximumImage : String? = null
)
