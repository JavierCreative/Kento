package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.anime.Anime


interface PopularAnimesDataSource {
    suspend fun get(page : Int) : List<Anime>?
}