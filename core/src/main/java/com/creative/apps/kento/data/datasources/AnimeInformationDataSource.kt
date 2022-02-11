package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.anime.Anime

interface AnimeInformationDataSource {
    suspend fun get(id : Int) : Anime?
}