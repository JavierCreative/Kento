package com.creative.apps.kento.domain.remote.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    @SerialName("mal_id") val id: Int? = 0,
    val url: String? = "",
    val images: Images? = null,
    val trailer: Trailer? = null,
    val title: String? = "",
    @SerialName("title_synonyms") val synonyms: List<String>? = null,
    val type: String? = "",
    val source: String? = "",
    val episodes: Int? = 0,
    val status: String? = "",
    val airing: Boolean? = false,
    val duration: String? = "",
    val rating: String? = "",
    val aired: Aired? = null,
    val broadcast: Broadcast? = null,
    val score: Double? = 0.0,
    @SerialName("scored_by") val scoredBy: Long? = 0,
    val rank: Int? = 0,
    val popularity: Int? = 0,
    val members: Int? = 0,
    val synopsis: String? = "",
    val season: String? = "",
    val year: Int? = 0,
    val producers: List<Producer>? = null,
    val licensors: List<Licensor>? = null,
    val studios: List<Studio>? = null,
    val genres: List<Genre>? = null,
    val demographics: List<Demographic>? = null

)