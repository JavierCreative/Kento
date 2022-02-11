package com.creative.apps.kento.domain.remote.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Demographic(
    @SerialName("mal_id") val id : Int? = null,
    val type : String? = null,
    val name : String? = null,
)
