package com.creative.apps.kento.domain.remote.genres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(@SerialName("mal_id")
                 val id : Int,
                 val name : String,
                 val url : String,
                 val count : Int)
