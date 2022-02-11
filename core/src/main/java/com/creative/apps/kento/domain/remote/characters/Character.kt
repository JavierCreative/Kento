package com.creative.apps.kento.domain.remote.characters

import com.creative.apps.kento.domain.remote.anime.Images
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName("mal_id") val id : Int? = null,
    val images : Images? = null,
    val name : String? = null
)
